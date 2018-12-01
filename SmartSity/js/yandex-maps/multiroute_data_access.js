
// Создаем карту с добавленной на нее кнопкой.
var myMap;
var routeTypeSelector;

var currentRoute = null;

function createMap() {
    // Создаём выпадающий список для выбора типа маршрута.
    routeTypeSelector = new ymaps.control.ListBox({
        data: {
            content: 'Как добраться'
        },
        items: [
            //new ymaps.control.ListBoxItem({data: {content: "Авто"},state: {selected: true}}),
            new ymaps.control.ListBoxItem({data: {content: "Общественным транспортом"},state: {selected: true}}),
            new ymaps.control.ListBoxItem({data: {content: "Пешком"}})
        ],
        options: {
            itemSelectOnClick: false
        }
    })

    myMap = new ymaps.Map(window.yamaps_map, {
        center: [55.750625, 37.626],
        zoom: 7,
        controls: [routeTypeSelector]
    }, {
        buttonMaxWidth: 300
    });
}

/** @param {Array} points */
function createRoute(points) {
    // Создаем модель мультимаршрута.
    var multiRouteModel = new ymaps.multiRouter.MultiRouteModel(
        points, {
            // Путевые точки можно перетаскивать.
            // Маршрут при этом будет перестраиваться.
            //wayPointDraggable: false,
            boundsAutoApply: true,
            routingMode: "masstransit"
        }),

        // Создаём выпадающий список для выбора типа маршрута.
        /*routeTypeSelector = new ymaps.control.ListBox({
            data: {
                content: 'Как добраться'
            },
            items: [
                //new ymaps.control.ListBoxItem({data: {content: "Авто"},state: {selected: true}}),
                new ymaps.control.ListBoxItem({data: {content: "Общественным транспортом"},state: {selected: true}}),
                new ymaps.control.ListBoxItem({data: {content: "Пешком"}})
            ],
            options: {
                itemSelectOnClick: false
            }
        }),*/
        // Получаем прямые ссылки на пункты списка.
        //autoRouteItem = routeTypeSelector.get(0),
        masstransitRouteItem = routeTypeSelector.get(0),
        pedestrianRouteItem = routeTypeSelector.get(1);

    // Подписываемся на события нажатия на пункты выпадающего списка.
    //autoRouteItem.events.add('click', function (e) { changeRoutingMode('auto', e.get('target')); });

    masstransitRouteItem.events.add('click', function (e) { changeRoutingMode('masstransit', e.get('target')); });
    pedestrianRouteItem.events.add('click', function (e) { changeRoutingMode('pedestrian', e.get('target')); });

    ymaps.modules.require([
        'MultiRouteCustomView'
    ], function (MultiRouteCustomView) {
        // Создаем экземпляр текстового отображения модели мультимаршрута.
        // см. файл custom_view.js
        new MultiRouteCustomView(multiRouteModel);
    });

    

    // Создаем на основе существующей модели мультимаршрут.
    multiRoute = new ymaps.multiRouter.MultiRoute(multiRouteModel, {
        // Путевые точки можно перетаскивать.
        // Маршрут при этом будет перестраиваться.
        wayPointDraggable: false,
        boundsAutoApply: true
    });


    if(currentRoute) {
        myMap.geoObjects.remove(currentRoute);
    }

    // Добавляем мультимаршрут на карту.
    myMap.geoObjects.add(multiRoute);
    currentRoute = multiRoute;

    function changeRoutingMode(routingMode, targetItem) {
        multiRouteModel.setParams({ routingMode: routingMode }, true);

        // Отменяем выбор элементов.
        //autoRouteItem.deselect();
        masstransitRouteItem.deselect();
        pedestrianRouteItem.deselect();

        // Выбираем элемент и закрываем список.
        targetItem.select();
        routeTypeSelector.collapse();
    }
}

//ymaps.ready(init);
