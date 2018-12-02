'use strict';
(function(w) {
    /* design */
    
    var Panels = {
        Home: {
            container: $("#panel-home"),
            microphoneIcon: {
                t1t: $("#t1t"),
                t2t: $("#t2t"),
            },
            voiceText: $("#voiceText"),
            clock: $("#label-clock")
        },

        Maps: {
            container: $("#panel-maps"),
            map: $("#ya-map"),
            map2: $("#yamaps-map-solo"),
            helpinfo: $("#ya-map-viewContainer")
        },

        Sos: {
            container: $("#panel-sos")
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
            Panels.Maps.helpinfo.text("");
            //createMap(points);
            createRoute(points);
            Animation.fadeIn(Panels.Maps.container);
        },

        /** @param {Array'Шоколадница')} points */
        buildRouteAndShowMapSolo: function(points,text) {
            createRoute(points);
            setSolomap(text);
            Panels.Maps.helpinfo.text("");
            Animation.fadeIn(Panels.Maps.container);
        }

    };
    


    setInterval(() => {
        var date = new Date();
        Panels.Home.clock.text(__toStrTime(date.getHours()) + ":" + __toStrTime(date.getMinutes()) + ":" + __toStrTime(date.getSeconds()));
    }, 120);

    function __toStrTime(num) {
        return ("0" + num).slice(-2);
    }
    //main
    YandexMaps.initialize('yamaps-map', function(){
        createMap();
        // var testPoints = [ "тюмень, сквер имени немцова", "тюмень, ленина 4" ];
        // YandexMaps.buildRouteAndShowMap(testPoints);
        
    });

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
        Panels.Home.voiceText.text(text);
        text = text.toLowerCase();
        var regexp = "(скорая|помогите|спасите|mayday)"; 
        if(text.search(regexp) != -1 ){
            voiseText("Звоню в службу спасения");
            SosEpelepsity();
            setTimeout(function(){ 
                reset();
            }, 9000);
            type = 4;
        }
        switch (type) {
            case 1:
            regexp = "остановка"; 
            if(text.search(regexp) != -1 ){
                type = 2;
                activPosition();
                voiceRecognier.abort();
            }
            break;
            case 2:
            if(!result.isFinal) break;
            regexp = "(как добраться до|как проехать до|маршрут до|как доехать до)"; 
            if(text.search(regexp) != -1 ) {
                text = text.split(" до ", 2);
                text = text[text.length-1];
                inactivPosition();
                var testPoints = [ myPoint, "тюмень, "+text ];
                YandexMaps.buildRouteAndShowMap(testPoints);
                voiseText("Маршрут построен");
                hideMainPanel();
                Animation.fadeIn(Panels.Maps.container);

                setTimeout(function(){ 
                    reset();
                }, 9000);
                type = 1;
            }
            regexp = "(где я|что рядом|какая остановка)"; 
            if(text.search(regexp) != -1 ) {
                inactivPosition();
                var testPoints = [myPoint];
                
                YandexMaps.buildRouteAndShowMap(testPoints);
                Panels.Maps.helpinfo.hide();
                hideMainPanel();
                Animation.fadeIn(Panels.Maps.container);
                voiseText("Вы находитесь здесь");
                setTimeout(function(){ 
                    reset();
                }, 9000);
                type = 1;
            }
            regexp = "(найди|найти)"; 
            if(text.search(regexp) != -1 ) {
                 var group = /(найти |найди )(.*)/g.exec(text);
                if(group[2]) {
                    text = group[2];
                inactivPosition();
                var testPoints = [myPoint];
                YandexMaps.buildRouteAndShowMapSolo(testPoints,text.trim());
                hideMainPanel();
                Animation.fadeIn(Panels.Maps.map2);
                setTimeout(function(){ 
                    reset();
                }, 15000);
                type = 1;                
                }
            }
            break;
        }
    };
    voiceRecognier.start();


    function reset() {
        Animation.fadeOut(Panels.Maps.container.hide(100));
        Animation.fadeIn(Panels.Home.container);
        Animation.fadeIn(Panels.Maps.map2);
        Panels.Maps.helpinfo.show();
        inactivPosition();
        voiceRecognier.start();
        type = 1
    }

    function hideMainPanel() {
        Animation.fadeOut(Panels.Home.container);
    }

    function activPosition() {
        Panels.Home.microphoneIcon.t2t.addClass("pulse2");
        Panels.Home.microphoneIcon.t1t.addClass("pulse1");
    }
    
    function inactivPosition() {
        Panels.Home.microphoneIcon.t2t.removeClass("pulse2");
        Panels.Home.microphoneIcon.t1t.removeClass("pulse1");
    }

    function SosEpelepsity() {
        Panels.Sos.container.show();

        setTimeout(function(){ 
            AntiSosEpelepsity();
        }, 9000);
    }

    function AntiSosEpelepsity() {
        Panels.Sos.container.hide();
        type = 1;
    }

    function voiseText(text) {
        var speech = new SpeechSynthesisUtterance(text);
        speech.lang = Languages.Russian;
        window.speechSynthesis.speak(speech);
    };  

    function getYaAPIData(){
       //https://api.rasp.yandex.net/v3.0/schedule/?apikey=79bb8005-89db-43e7-aca4-897b349b5ad4&
    }
})(window);
