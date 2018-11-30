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
    
    /* end design*/

    //maps
    var YandexMaps = {
        map: { },
        currentRoute: null,
    
        initialize: function(id) {
            YandexMaps.map = new ymaps.Map(id, {
                center: [57.154885, 65.532953],
                zoom: 12,
                controls: []
            }, {
                buttonMaxWidth: 350
            });
        },
    
        createRoute: function(points) {
            return new ymaps.multiRouter.MultiRoute({
                referencePoints: points,
                params: {
                    routingMode: 'masstransit'
                }
            }, {
                boundsAutoApply: true
            });
        },
    
        setRoute: function(route) {
            if(YandexMaps.currentRoute) {
                YandexMaps.map.geoObjects.remove(route)
            }
    
            YandexMaps.map.geoObjects.add(route);
        }
    };
    
   
    (function() {
        ymaps.ready(function() {
            YandexMaps.initialize('ya-map');
            
            // var route = YandexMaps.createRoute([ "тюмень, сквер имени немцова", "тюмень, ленина 4" ]);
            // YandexMaps.setRoute(route);

            Panels.Voice.microphoneIcon.addClass("pulse");
            
        });

        
    })();
    newFunction();

})(window);


function newFunction() {
    BoolleanComandVoice();
}

function BoolleanComandVoice() {
    var Languages = {
        Russian: 'ru-Ru',
        English: 'en-Us'
    };

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
    var voiceRecognier = new webkitSpeechRecognition();
    voiceRecognier.interimResults = true;
    voiceRecognier.lang = Languages.Russian;
    voiceRecognier.onend = function(e){
        BoolleanComandVoice();
    }
    voiceRecognier.onresult = function(e) {
        var result = e.results[e.resultIndex];
    
        var text = result[0].transcript;
        Panels.Voice.voiceText.text(text);
        text = text.toLowerCase()
        regexp = "будь добра моя остановока"; 
        if(text.search(regexp) != -1 ){
            voiceRecognier.abort()
            ComanVoiceActive();
        }
    }
    voiceRecognier.start();
  }

  function ComanVoiceActive(comandVoice) {
    var Languages = {
        Russian: 'ru-Ru',
        English: 'en-Us'
    };

    var voiceRecognier = new webkitSpeechRecognition();
    voiceRecognier.interimResults = true;
    voiceRecognier.lang = Languages.Russian;
    
    voiceRecognier.onresult = function(e) {
        var result = e.results[e.resultIndex];
    
        var text = result[0].transcript;
        Panels.Voice.voiceText.text(text);

        if(!result.isFinal) {
            //not final result

        } else {
            //final result
            //do work
            
            Panels.Voice.microphoneIcon.removeClass("pulse");
            var type = result[0].transcript;
            type = type.toLowerCase()
            regexp = "(как добраться до|как проехать до|маршрут до|как доехать до)"; 
            if(type.search(regexp) != -1 ) {
                type = type.split(" до ") 
                type = type[type.length-1]  
                Panels.Voice.microphoneIcon.removeClass("pulse");
                YandexMaps.setRoute(YandexMaps.createRoute([ "57.149868, 65.547018", "тюмень, "+type]));
                Panels.Maps.container.show();
            }
            Panels.Maps.container.show();
        }
    }

    voiceRecognier.start();
  }
