(function () {
  var button_l = $('#rsa_l');
  var button_u = $('#rsa_u');

  function loadAndCrupt() {
    $.ajax({
      url: 'res/jc.json',
      type: "GET",
      dataType: 'text',
      success: cript
    });
  }

  function loadAndCruptDecrupt() {
    $.ajax({
      url: 'res/jc.json',
      type: "GET",
      dataType: 'text',
      success: criptdecrupt
    });
  }

  var p = 3557, q = 2579;
  var n = p * q, eler = (p - 1) * (q - 1), e = 3, d = 6111579;
  console.log('n= ' + n);
  console.log('n= ' + p);
  console.log('n= ' + eler);
  console.log('n= ' + d);

  function cript(data) {
    debugger;
    data = JSON.stringify(data);
    var newString = "";
    for (var g = 0; g < data.length; g++) {
      newString += (String.fromCharCode((Math.pow(data.slice(g, 1).charCodeAt(0), 3) % n)));
    }
    download(newString, 'rsa_s.json')
  }

  function criptdecrupt(data) {
    debugger;
    data = JSON.stringify(data);
    var newString = "";
    for (var g = 0; g < data.length; g++) {
      var ocode = parseInt(data.slice(g, 1).charCodeAt(0)), ocode2 = parseInt(data.slice(g, 1).charCodeAt(1));
      var code2 =faststep(data.slice(g, 1).charCodeAt(0), 3, n) % n;
      var code = faststep((faststep(data.slice(g, 1).charCodeAt(0), 3, n) % n), d, n) % n;
      newString += (String.fromCharCode(faststep((faststep(data.slice(g, 1).charCodeAt(0), 3, n) % n), d, n) % n));
    }
    download(newString, 'rsa_u.json')
  }

  $('#rsa_l').on('click', loadAndCrupt);
  $('#rsa_u').on('click', loadAndCruptDecrupt);
})();


function download(data, filename) {
  var type = 'application/octet-stream';
  var file = new Blob([data], {type: type});
  if (window.navigator.msSaveOrOpenBlob) {
    window.navigator.msSaveOrOpenBlob(file, filename);
  } else {
    var a = document.createElement("a"), url = URL.createObjectURL(file);
    a.href = url;
    a.download = filename;
    document.body.appendChild(a);
    a.click();
    setTimeout(function () {
      document.body.removeChild(a);
      window.URL.revokeObjectURL(url);
    }, 0);
  }
}


function faststep (val, step, mod) {
  mod = parseInt(mod);
  s = 1; v = step; c = val;
  while (v != 0) {
    flag = 0;
    if (v%2 == 1) {
      if (!mod)
        s = s*c;
      else
        s = (s*c) % mod;
      v = (v-1)/2;
      if (!mod)
        c = c*c;
      else
        c = (c*c) % mod;
      flag = 1;
    }
    else {
      v = v/2;
    }
    if (!flag)
      if (!mod)
        c = c*c;
      else
        c = (c*c) % mod;
  }
  return s;
}
