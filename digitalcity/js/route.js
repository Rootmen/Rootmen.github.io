var YandexMaps = {
    map: { },
    currentRoute: null,

    initialize: function() {
        YandexMaps.map = new ymaps.Map('map', {
            center: [57.154885, 65.532953],
            zoom: 102,
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
    },

    setSolomap: function(route) {
        var searchControl = new ymaps.control.SearchControl({
            options: {
                provider: 'yandex#search'
            }
        });
        
        YandexMaps.map.controls.add(searchControl);
        searchControl.search(text);
    }
}

ymaps.ready(function() {
    YandexMaps.initialize();
    var route = YandexMaps.createRoute([ "тюмень, сквер имени немцова", "тюмень, ленина 4" ]);
    YandexMaps.setRoute(route);
    YandexMaps.setSolomap()
});