"use struct";
//var myDate = new Date();
var myDate = new Date();

Date.prototype.getWeek = function () {
    var date = new Date(this.getTime());
    date.setHours(0, 0, 0, 0);
    date.setDate(date.getDate() + 3 - (date.getDay() + 6) % 7);
    var week1 = new Date(date.getFullYear(), 0, 4);
    return 1 + Math.round(((date.getTime() - week1.getTime()) / 86400000
        - 3 + (week1.getDay() + 6) % 7) / 7);
};

Date.prototype.getWeekYear = function () {
    var date = new Date(this.getTime());
    date.setDate(date.getDate() + 3 - (date.getDay() + 6) % 7);
    return date.getFullYear();
};

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
    var mainTabel = $('<div style="margin: 3px; display: flex; flex-direction: column; border-bottom: 1pt solid black; justify-content: stretch; min-width: 900px; overflow: scroll;"></div>');
    var msg = $('<div style="flex: 0 0 45px; margin: 3px; margin-left: 40px;"></div>');
    var week = Object.keys(tabel);
    for (var k = 0; k < week.length; k++) {
        var day = tabel[week[k]];
        mainTabel.append($('<tr style="background:#F8CBAD; display:flex; flex-direction:row; align-content:center; text-align:center; font-weight: 700; flex: 0 0 46px; border: 1pt solid black; border-bottom: none"><td style="width: 35px; flex:0 0 94px;"></td><td colspan="2" style="border-left: 1.0pt solid black; flex: 1 1 1px; text-decoration-style: solid; vertical-align: middle;"><p>' + week[k] + '</p></td></tr>'));
        mainTabel.append(addDay(day))
    }
    tab.append(msg);
    tab.append(mainTabel);
    tab.append('<div style="flex: 1 1 1px;"></div>');
    $(document.body).append(tab);
    setInterval(function () {
        var newSubject = getNewSubject();
        myDate = new Date();
        var week = myDate.getWeek() % 2 !== 1 ? '1' : '2';
        msg.html('<p>' + newSubject.ms + '</p>' + '<p>' + ((newSubject.ms2) ? newSubject.ms2 : '') + '</p>' + '<p>Сейчас ' + week + ' неделя</p>');
        var tabelHtml = $(document.body).find('tr');
        tabelHtml.removeClass('color-mode');
        var weekDay = ['Понедельник', 'Вторник', 'Среда', 'Четверг', 'Пятница'], count = 0;
        for (var g = 0; g < 5; g++) {
            count++;
            if (weekDay[g] === newSubject.day) {
                count = count + newSubject.num;
                break;
            } else {
                count = count + tabel[weekDay[g]].length;
            }
        }
        $(tabelHtml[count]).addClass('color-mode');
    }, 450);

    function addDay(day) {
        var HTMLtabel = "";
        for (var k = 0; k < day.length; k++) {
            if (day[k].w1 === "replay") {
                HTMLtabel += '<tr style="display:flex; flex-direction:row; align-content:center; text-align:center; font-weight: 700; flex: 0 0 46px; border: 1pt solid black; border-bottom: none"><td style="width: 35px; flex:0 0 94px; background-color: #FCE4D6"><p>' + day[k].w2.time + '</p></td><td colspan="2" style="border-left: 1.0pt solid black; flex: 1 1 1px; text-decoration-style: solid; vertical-align: middle;"><p>' + day[k].w2.subject + '</p></td></tr>'
            } else if (day[k].w1 === null) {
                HTMLtabel += '<tr style="display:flex; flex-direction:row; align-content:center; text-align:center; font-weight: 700; flex: 0 0 46px; border: 1pt solid black; border-bottom: none"><td style="width: 35px; flex:0 0 94px; background-color: #FCE4D6"><p>' + day[k].w2.time + '</p></td><td style="border-left: 1.0pt solid black; flex: 1 1 1px; text-decoration-style: solid; vertical-align: middle; background-color: #DDEBF7;"><p>----------------------</p></td><td style="border-left: 1.0pt solid black; flex: 1 1 1px; text-decoration-style: solid; vertical-align: middle;"><p>' + day[k].w2.subject + '</p></td></tr>'
            } else if (day[k].w2 === null) {
                HTMLtabel += '<tr style="display:flex; flex-direction:row; align-content:center; text-align:center; font-weight: 700; flex: 0 0 46px; border: 1pt solid black; border-bottom: none"><td style="width: 35px; flex:0 0 94px; background-color: #FCE4D6"><p>' + day[k].w1.time + '</p></td><td style="border-left: 1.0pt solid black; flex: 1 1 1px; text-decoration-style: solid; vertical-align: middle;"><p>' + day[k].w1.subject + '</p></td><td style="border-left: 1.0pt solid black; flex: 1 1 1px; text-decoration-style: solid; vertical-align: middle; background-color: #DDEBF7;"><p>----------------------</p></td></tr>'
            } else {
                HTMLtabel += '<tr style="display:flex; flex-direction:row; align-content:center; text-align:center; font-weight: 700; flex: 0 0 46px; border: 1pt solid black; border-bottom: none"><td style="width: 35px; flex:0 0 94px; background-color: #FCE4D6"><p>' + day[k].w1.time + '</p></td><td style="border-left: 1.0pt solid black; flex: 1 1 1px; text-decoration-style: solid; vertical-align: middle;"><p>' + day[k].w1.subject + '</p></td><td style="border-left: 1.0pt solid black; flex: 1 1 1px; text-decoration-style: solid; vertical-align: middle;"><p>' + day[k].w2.subject + '</p></td></tr>'
            }
        }
        return $(HTMLtabel);
    }

    function getNewSubject() {
        var thisSubject = null;
        var day = myDate.getDay() - 1;
        var weekOrigin = myDate.getWeek() % 2 !== 1 ? 'w1' : 'w2';
        var week = myDate.getWeek() % 2 !== 1 ? 'w1' : 'w2';
        if (day > 5 || day < 0) {
            return {ms: "Выходные"}
        }
        var weekDay = ['Понедельник', 'Вторник', 'Среда', 'Четверг', 'Пятница'];
        var diff, house, mun;
        var dayInfo = tabel[weekDay[day]];
        for (var g = 0; g < dayInfo.length; g++) {
            var subjectThis = dayInfo[g][week];
            if (subjectThis === null) continue;
            if (subjectThis === 'replay') {
                week = 'w2';
                subjectThis = dayInfo[g][week];
            }
            var time = subjectThis.time.split('-');
            var startDate = new Date(myDate.getTime()), endDate = new Date(myDate.getTime());
            startDate.setHours(time[0].split(':')[0], time[0].split(':')[1], 0, 0);
            endDate.setHours(time[1].split(':')[0], time[1].split(':')[1], 0, 0);
            if (startDate.getTime() <= myDate.getTime() && myDate.getTime() <= endDate.getTime()) {
                var answer = {};
                answer.day = weekDay[day];
                answer.num = g;
                answer.ms = "Сейчас идет \"" + subjectThis.subject + "\"";
                answer.ms2 = '';
                var nextPara = dayInfo[g + 1];
                if (nextPara === null || nextPara === undefined) {
                    answer.ms2 = 'Пары на сегодня закончились';
                    return answer;
                } else if (nextPara[weekOrigin] === null || nextPara[weekOrigin] === undefined) {
                    answer.ms2 = 'Пары на сегодня закончились';
                    return answer;
                } else if (nextPara[weekOrigin] === 'replay') {
                    weekOrigin = 'w2';
                }
                var timeTo = new Date(myDate.getTime());
                var timeLocal = nextPara[weekOrigin].time.split('-');
                startDate.setHours(timeLocal[0].split(':')[0], timeLocal[0].split(':')[1], 0, 0);
                var diff = Math.abs(startDate.getTime() - myDate.getTime());
                var house = Math.floor((diff / (1000 * 60 * 60)) % 24);
                var mun = Math.floor(((diff / (1000 * 60)) % 60));
                answer.ms2 = "Седующая пара \"" + nextPara[weekOrigin].subject + "\" через: " + house + ":" + mun;
                return answer;
            }
        }
        for (g = 1; g < dayInfo.length; g++) {
            var subjectThis = dayInfo[g][week];
            if (subjectThis === null) continue;
            if (subjectThis === 'replay') {
                week = 'w2';
                subjectThis = dayInfo[g][week];
            }
            var time = subjectThis.time.split('-');
            var time2 = dayInfo[g - 1][week].time.split('-');
            var startDate = new Date(myDate.getTime()), endDate = new Date(myDate.getTime());
            startDate.setHours(time2[1].split(':')[0], time2[1].split(':')[1], 0, 0);
            endDate.setHours(time[0].split(':')[0], time[0].split(':')[1], 0, 0);
            if (startDate.getTime() < myDate.getTime() && myDate.getTime() < endDate.getTime()) {
                var answer = {};
                answer.ms = "Сейчас перемена";
                answer.day = weekDay[day];
                answer.num = g;
                var nextPara = dayInfo[g];
                if (nextPara === null || nextPara === undefined) {
                    answer.ms2 = 'Пары на сегодня закончились';
                    return answer;
                } else if (nextPara[weekOrigin] === null || nextPara[weekOrigin] === undefined) {
                    answer.ms2 = 'Пары на сегодня закончились';
                    return answer;
                } else if (nextPara[weekOrigin] === 'replay') {
                    weekOrigin = 'w2';
                }
                endDate.setHours(time2[0].split(':')[0], time2[0].split(':')[1], 0, 0);
                var diff = Math.abs(endDate.getTime() - myDate.getTime());
                var house = Math.floor((diff / (1000 * 60 * 60)) % 24);
                var mun = Math.floor(((diff / (1000 * 60)) % 60));
                answer.ms2 = "Седующая пара \"" + nextPara[weekOrigin].subject + "\", а эта закончиться через: " + house + ":" + mun;
                return answer;
            }
        }
        var firstDay = tabel[weekDay[day]][0].w1;
        if (firstDay === 'replay' || firstDay === null) {
            firstDay = tabel[weekDay[day]][0].w2;
        }
        endDate = new Date(myDate.getTime());
        var timeL = firstDay.time.split('-');
        endDate.setHours(timeL[0].split(':')[0], timeL[0].split(':')[1], 0, 0);
        diff = Math.abs(endDate.getTime() - myDate.getTime());
        house = Math.floor((diff / (1000 * 60 * 60)) % 24);
        mun = Math.floor(((diff / (1000 * 60)) % 60));
        return {ms: 'Пары еще не начались, до начала пар ' + house + ':' + mun, day: weekDay[day], num: 1};
    }
}());