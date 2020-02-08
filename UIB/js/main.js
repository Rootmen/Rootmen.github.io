var myDate = new Date();

var tabel = {
    "Понедельник": [{w1: null, w2: {time: '11:20-12:50', subject: 'Открытые программные платформы(лб) Абрамов 1330'}},
        {
            w1: {time: '13:20-14:50', subject: 'Открытые программные платформы(лк) Абрамов 1523'},
            w2: {time: '13:20-14:50', subject: 'Открытые программные платформы(лк) Абрамов 4511'}
        },
        {w1: 'replay', w2: {time: '15:00-16:30', subject: 'Гуманитарные аспекты инф. Без. (пр) Шпагина 1325'}},
        {w1: null, w2: {time: '16:40-18:10', subject: 'Открытые программные платформы(лб) Абрамов 1330'}}],

    "Вторник": [{w1: 'replay', w2: {time: '16:40-18:10', subject: 'Защита программ и данных (лб) Малинский 1330'}},
        {
            w1: {time: '18:20-19:50', subject: 'Аудит инф. Безопасности (пр) Антошкин 1325'},
            w2: {time: '18:20-19:50', subject: 'Защита программ и данных (лб) Малинский 1330'}
        }],

    "Среда": [{
        w1: {time: '15:00-16:30', subject: 'Гуманитарные аспекты инф. Без. (лк) Шпагина 1329'},
        w2: {time: '15:00-16:30', subject: 'Гуманитарные аспекты инф. Без. (пр) Шпагина 1329'}
    },
        {w1: 'replay', w2: {time: '16:40-18:10', subject: 'Гуманитарные аспекты инф. Без. (лк) Шпагина 1329'}},
        {w1: 'replay', w2: {time: '18:20-19:50', subject: 'Защита программ и данных (лк) Малинский 1329'}}],

    "Четверг": [
        {w1: 'replay', w2: {time: '16:40-18:10', subject: 'Аудит инф. Безопасности (пр) Антошкин 1325'}},
        {w1: 'replay', w2: {time: '18:20-19:50', subject: 'Аудит инф. Безопасности (лк) Антошкин 1329'}}],

    "Пятница": [
        {w1: 'replay', w2: {time: '11:20-12:50', subject: 'Открытые программные платформы(лб)Абрамов 1330'}},
        {w1: 'replay', w2: {time: '13:20-14:50', subject: 'Комп. Обеспечение защиты об. Инф. (пр) Антошкин 1325'}},
        {
            w1: {time: '15:00-16:30', subject: 'Комп. Обеспечение защиты об. Инф. (лк) Антошкин 1329'},
            w2: {time: '15:00-16:30', subject: 'Аудит инф. Безопасности (пр) Антошкин 1325'}
        },
        {w1: 'replay', w2: {time: '16:40-18:10', subject: 'Комп. Обеспечение защиты об. Инф. (лк) Антошкин 1329'}}]
};


(function main() {
    var tab = $('<div style=" position:fixed; top:0; left:0; width:100%; height:100%; display:flex; flex-direction: column; justify-content: stretch;"></div>');
    var mainTabel = $('<div style="margin: 3px; display: flex; flex-direction: column; border-bottom: 1pt solid black; justify-content: stretch;"></div>');
    var week = Object.keys(tabel);
    for (var k = 0; k < week.length; k++) {
        var day = tabel[week[k]];
        mainTabel.append($('<tr style="background:#F8CBAD; display:flex; flex-direction:row; align-content:center; text-align:center; font-weight: 700; flex: 0 0 46px; border: 1pt solid black; border-bottom: none"><td style="width: 35px; flex:0 0 94px;"></td><td colspan="2" style="border-left: 1.0pt solid black; flex: 1 1 1px;">' + week[k] + '</td></tr>'));
    }
    tab.append(mainTabel);
    tab.append('<div style="flex: 1 1 1px;"></div>');
    $(document.body).append(tab);

}());