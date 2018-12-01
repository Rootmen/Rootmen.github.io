'use strict';
(function(w) {
    /* design */
    
    var Panels = {
        Voice: {
            container: $("#panel-voice"),
            microphoneIcon: $("#microphoneIcon"),
            voiceText: $("#voiceText")
        },

        Maps: {
            container: $("#panel-maps"),
            map: $("#ya-map")
        }
    }

    var Animation = {
        fadeOut: function(el) {
            el.fadeOut({
                duration: 600
            });
        },

        fadeIn: function(el) {
            el.fadeIn({
                duration: 600
            });
        }
    };
    
    /* end design*/

    /* commands */

    function processCommand(cmdRaw) {
        //var cmd = cmdRaw.toLowerCase();

    }

    var CommandProcesser = {
        /** @param {String} cmd */
        processOK: function(cmd) {
            return cmd.match(/остановка/gi);
        },

        processRoute: function(cmd) {

        }
    };

    //maps
    var myPoint = [ 57.159746, 65.522072 ];
    var YandexMaps = {
        initialize: function(map, callback) {
            window.yamaps_map = map;
            ymaps.ready(callback);
            
        },

        /** @param {Array} points */
        buildRouteAndShowMap: function(points) {
            //createMap(points);
            createRoute(points);
            Animation.fadeIn(Panels.Maps.container);
        }
    };
    
   

    window.speak = function(text) {
        var speech = new SpeechSynthesisUtterance('тест');
        speech.lang = Languages.Russian;
        window.speechSynthesis.speak(speech);
    };  


    //main
    YandexMaps.initialize('yamaps-map', function(){
        createMap();
        // var testPoints = [ "тюмень, сквер имени немцова", "тюмень, ленина 4" ];
        // YandexMaps.buildRouteAndShowMap(testPoints);
        
    });
    Panels.Voice.microphoneIcon.removeClass("pulse");
    var Languages = {
        Russian: 'ru-Ru',
        English: 'en-Us'
    };
    var type = 1;
    var voiceRecognier = new webkitSpeechRecognition();
    voiceRecognier.interimResults = true;
    voiceRecognier.lang = Languages.Russian;
    voiceRecognier.onend = function(e){
        voiceRecognier.start();
    };

    voiceRecognier.onresult = function(e) {
        var result = e.results[e.resultIndex];
        var text = result[0].transcript;
        Panels.Voice.voiceText.text(text);
        text = text.toLowerCase();
        switch (type) {
            case 1:
            var regexp = "будь добра моя остановка"; 
            if(text.search(regexp) != -1 ){
                type = 2;
                Panels.Voice.microphoneIcon.addClass("pulse");
                voiceRecognier.abort();
            }
            break;
            case 2:
            if(!result.isFinal) break;
            regexp = "(как добраться до|как проехать до|маршрут до|как доехать до)"; 
            if(text.search(regexp) != -1 ) {
                text = text.split(" до ") 
                text = text[text.length-1]  
                Panels.Voice.microphoneIcon.removeClass("pulse");
                var testPoints = [ "тюмень, сквер имени немцова", "тюмень, "+text ];
                YandexMaps.buildRouteAndShowMap(testPoints);
                
                hideMainPanel();
                Animation.fadeIn(Panels.Maps.container);

                setTimeout(function(){ 
                    reset();
                }, 3000);
                type = 1;
                
            }
            break;
        }
    }
    voiceRecognier.start();


    function reset() {
        Animation.fadeOut(Panels.Maps.container);
        Animation.fadeIn(Panels.Voice.container);
        Panels.Voice.microphoneIcon.removeClass("pulse");
        voiceRecognier.start();
    }

    function hideMainPanel() {
        Animation.fadeOut(Panels.Voice.container);
    }

    

    

})(window);
