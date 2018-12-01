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
    
    var Languages = {
        Russian: 'ru-Ru',
        English: 'en-Us'
    };

  
    (function() {
        ymaps.ready(function() {
            YandexMaps.initialize('ya-map');
            
            var route = YandexMaps.createRoute([ "тюмень, сквер имени немцова", "тюмень, ленина 4" ]);
            YandexMaps.setRoute(route);

            Panels.Voice.microphoneIcon.addClass("pulse");
            
        });

        
    })();
   newFunction();

})(window);

function newFunction() {
    type = 1;
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
        switch (type) {
            case 1:
            text = "будь добра моя остановка"
            regexp = "будь добра моя остановка"; 
            if(text.search(regexp) != -1 ){
                type = 2;
                voiceRecognier.abort()
            }
            break;
            case 2:
            if(!result.isFinal) break;
            text = "как добраться до центральеой улицы"
            regexp = "(как добраться до|как проехать до|маршрут до|как доехать до)"; 
            if(text.search(regexp) != -1 ) {
                text = text.split(" до ") 
                text = text[text.length-1]  
                Panels.Voice.microphoneIcon.removeClass("pulse");
                
                type = 1;
            }
            break;
        }
    }
    voiceRecognier.start();
  }