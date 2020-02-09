//var myDate = new Date();
var myDate = new Date(2020, 1,10, 13,50);

Date.prototype.getWeek = function () {
    var date = new Date(this.getTime());
    date.setHours(0, 0, 0, 0);
    // Thursday in current week decides the year.
    date.setDate(date.getDate() + 3 - (date.getDay() + 6) % 7);
    // January 4 is always in week 1.
    var week1 = new Date(date.getFullYear(), 0, 4);
    // Adjust to Thursday in week 1 and count number of weeks from date to week1.
    return 1 + Math.round(((date.getTime() - week1.getTime()) / 86400000
        - 3 + (week1.getDay() + 6) % 7) / 7);
}

// Returns the four-digit year corresponding to the ISO week of the date.
Date.prototype.getWeekYear = function () {
    var date = new Date(this.getTime());
    date.setDate(date.getDate() + 3 - (date.getDay() + 6) % 7);
    return date.getFullYear();
}

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
    var week = Object.keys(tabel);
    var newSubject = getNewSubject();
    console.log(newSubject);
    for (var k = 0; k < week.length; k++) {
        var day = tabel[week[k]];
        mainTabel.append($('<tr style="background:#F8CBAD; display:flex; flex-direction:row; align-content:center; text-align:center; font-weight: 700; flex: 0 0 46px; border: 1pt solid black; border-bottom: none"><td style="width: 35px; flex:0 0 94px;"></td><td colspan="2" style="border-left: 1.0pt solid black; flex: 1 1 1px; text-decoration-style: solid; vertical-align: middle;"><p>' + week[k] + '</p></td></tr>'));
        mainTabel.append(addDay(day))
    }
    tab.append(mainTabel);
    tab.append('<div style="flex: 1 1 1px;"></div>');
    $(document.body).append(tab);

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
        var week = myDate.getWeek();
        if (day > 5 || day < 0) {
            return {ms: "Выходные"}
        }
        var weekDay = ['Понедельник', 'Вторник', 'Среда', 'Четверг', 'Пятница'];
        var dayInfo = tabel[weekDay[day]];
        for (var g = 0; g < dayInfo.length; g++) {
            var subjectThis = dayInfo[g].w2 || dayInfo.w1;
            var time = subjectThis.time.split('-');
            var startDate = new Date(myDate.getTime()), endDate = new Date(myDate.getTime());
            startDate.setHours(time[0].split(':')[0], time[0].split(':')[1], 0, 0);
            endDate.setHours(time[1].split(':')[0], time[1].split(':')[1], 0, 0);
            if (startDate.getTime() < myDate.getTime() && myDate.getTime() < endDate.getTime()) {
                if (dayInfo[g].w1 === "replay" || dayInfo[g].w1 === null) {
                    return dayInfo[g].w2;
                } else {
                    return dayInfo[g].w1;
                }
            }
        }
        return null;
    }
}());