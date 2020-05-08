(function RSA() {
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
    data = JSON.parse(data);
    var newA = [], newB = [], newC = [], g;
    /*for (g = 0; g < data.A.length; g++) {
      newA.push((Math.pow(data.A[g], 3) % n));
    }
    for (g = 0; g < data.B.length; g++) {
      newB.push((Math.pow(data.B[g], 3) % n));
    }
    for (g = 0; g < data.C.length; g++) {
      newC.push((Math.pow(data.C[g], 3) % n));
    }*/
    for (g = 0; g < data.A.length; g++) {
      newA.push(data.A[g] + 33);
    }
    for (g = 0; g < data.B.length; g++) {
      newB.push(data.B[g] + 33);
    }
    for (g = 0; g < data.C.length; g++) {
      newC.push(data.C[g] + 33);
    }
    data.A = newA;
    data.B = newB;
    data.C = newC;
    download(JSON.stringify(data), 'rsa_s.json')
  }

  function criptdecrupt(data) {
    data = JSON.parse(data);
    var newA = [], newB = [], newC = [], g;
    for (g = 0; g < data.A.length; g++) {
      newA.push(faststep((faststep(data.A[g], 3, n) % n), d, n) % n);
    }
    for (g = 0; g < data.B.length; g++) {
      newB.push(faststep((faststep(data.B[g], 3, n) % n), d, n) % n);
    }
    for (g = 0; g < data.C.length; g++) {
      newC.push(faststep((faststep(data.C[g], 3, n) % n), d, n) % n);
    }
    data.A = newA;
    data.B = newB;
    data.C = newC;
    download(JSON.stringify(data), 'rsa_u.json')
  }

  $('#rsa_l').on('click', loadAndCrupt);
  $('#rsa_u').on('click', loadAndCruptDecrupt);

  function faststep(val, step, mod) {
    mod = parseInt(mod);
    s = 1;
    v = step;
    c = val;
    while (v != 0) {
      flag = 0;
      if (v % 2 == 1) {
        if (!mod)
          s = s * c;
        else
          s = (s * c) % mod;
        v = (v - 1) / 2;
        if (!mod)
          c = c * c;
        else
          c = (c * c) % mod;
        flag = 1;
      } else {
        v = v / 2;
      }
      if (!flag)
        if (!mod)
          c = c * c;
        else
          c = (c * c) % mod;
    }
    return s;
  }
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


(function DES_f() {
  var button_l = $('#des_l');
  var button_u = $('#des_u');
  let Key = makeid(8);
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

  var desP = DES.create({
    type: 'encrypt',
    key: [ 0x13, 0x34, 0x57, 0x79, 0x9B, 0xBC, 0xDF, 0xF1 ]
  });

  function cript(data) {
    data = JSON.parse(data);
    var newA = [], newB = [], newC = [], g;
    for (g = 0; g < data.A.length; g++) {
      newA.push((Math.pow(data.A[g], 3) % n));
    }
    for (g = 0; g < data.B.length; g++) {
      newB.push((Math.pow(data.B[g], 3) % n));
    }
    for (g = 0; g < data.C.length; g++) {
      newC.push((Math.pow(data.C[g], 3) % n));
    }
    data.A = newA;
    data.B = newB;
    data.C = newC;
    download(JSON.stringify(data), 'rsa_s.json')
  }

  function criptdecrupt(data) {
    data = JSON.parse(data);
    var newA = [], newB = [], newC = [], g;
    for (g = 0; g < data.A.length; g++) {
      newA.push(faststep((faststep(data.A[g], 3, n) % n), d, n) % n);
    }
    for (g = 0; g < data.B.length; g++) {
      newB.push(faststep((faststep(data.B[g], 3, n) % n), d, n) % n);
    }
    for (g = 0; g < data.C.length; g++) {
      newC.push(faststep((faststep(data.C[g], 3, n) % n), d, n) % n);
    }
    data.A = newA;
    data.B = newB;
    data.C = newC;
    download(JSON.stringify(data), 'rsa_u.json')
  }

  $('#des_l').on('click', loadAndCrupt);
  $('#des_u').on('click', loadAndCruptDecrupt);

  function faststep(val, step, mod) {
    mod = parseInt(mod);
    s = 1;
    v = step;
    c = val;
    while (v != 0) {
      flag = 0;
      if (v % 2 == 1) {
        if (!mod)
          s = s * c;
        else
          s = (s * c) % mod;
        v = (v - 1) / 2;
        if (!mod)
          c = c * c;
        else
          c = (c * c) % mod;
        flag = 1;
      } else {
        v = v / 2;
      }
      if (!flag)
        if (!mod)
          c = c * c;
        else
          c = (c * c) % mod;
    }
    return s;
  }
})();
