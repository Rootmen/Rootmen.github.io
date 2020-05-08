// Avoid `console` errors in browsers that lack a console.
(function() {
  var method;
  var noop = function () {};
  var methods = [
    'assert', 'clear', 'count', 'debug', 'dir', 'dirxml', 'error',
    'exception', 'group', 'groupCollapsed', 'groupEnd', 'info', 'log',
    'markTimeline', 'profile', 'profileEnd', 'table', 'time', 'timeEnd',
    'timeline', 'timelineEnd', 'timeStamp', 'trace', 'warn'
  ];
  var length = methods.length;
  var console = (window.console = window.console || {});

  while (length--) {
    method = methods[length];

    // Only stub undefined methods.
    if (!console[method]) {
      console[method] = noop;
    }
  }
}());

// Place any jQuery/helper plugins in here.
function DEC(data, key, do_encrypt) {
  let dataNoEncrupt = data, originKey = key, temp = new Array(65), rezult = new Array(65);
  let thisKey = new Array(57);
  let textLog = '';
  let KS = new Array(16);
  let CD = new Array(57);
  let L = new Array(33);		// left half of current data
  let R = new Array(33);		// right half of current data
  this.premutatuon = function (out, inu, perm) {
    try {
      for (let i = 1; i < perm.length; i++) {
        out[i] = inu[perm[i]];
      }
    } catch (e) {
      alert('Ошибка нахожения элемента в предутсановленных константах во время премутации')
      out = null;
    } finally {
      return out
    }
  };
  this.printArray = function (array, spice) {
    let outputFunction = '';
    for (let g = 1; g < array.length; g++) {
      if ((g % spice) === 1) {
        outputFunction += ' ';
      }
      outputFunction += array[g];
    }
    return outputFunction;
  };

  this.init = function () {
    this.premutatuon(CD, originKey, PC_1_perm);
    textLog = 'Пукт №1\n';
    textLog += 'Генерирование ключей\n';
    textLog += 'Исходный ключ: ' + this.printArray(originKey, 7) + '\n';
    textLog += 'Ключ после перестановки: ' + this.printArray(CD, 7) + '\n';
    textLog += 'Генерация подключей раудов\n';
    for (let g = 0; g < 16; g++) {
      KS[g] = new Array(49);
      if (g == 1 || g == 2 || g == 9 || g == 16)
        this.shift_CD_1(CD);
      else
        this.shift_CD_2(CD);
      this.premutatuon(KS[g], CD, PC_2_perm);
      textLog += 'CD[' + g + ']: ' + this.printArray(CD, 7) + '\n';
      textLog += 'KS[' + g + ']: ' + this.printArray(KS[g], 7) + '\n';
    }
    this.premutatuon(temp, dataNoEncrupt, IP_perm);
    textLog += 'Исходны данные: ' + this.printArray(dataNoEncrupt, 7) + '\n';
    textLog += 'Первоначальная перестановка: ' + this.printArray(temp, 7) + '\n';
    for (let i = 1; i <= 32; i++) {
      L[i] = temp[i];
      R[i] = temp[i + 32];
    }
    textLog += 'Деление на L и R\n';
    textLog += 'L: ' + this.printArray(L, 7) + '\n';
    textLog += 'R: ' + this.printArray(R, 7) + '\n';
    if (do_encrypt) {
      for (let i = 0; i < 16; i++) {
        textLog += 'Раунд' + (i + 1) + '\n';
        this.des_round(L, R, KS[i]);
      }
    } else {
      for (let i = 15; i >= 0; i--) {
        textLog += 'Раунд' + (16 - i) + '\n';
        this.des_round(L, R, KS[i]);
      }
    }
    for (let i = 1; i <= 32; i++) {
      temp[i] = R[i];
      temp[i + 32] = L[i];
    }
    textLog += 'LR[16]: ' + this.printArray(temp, 8) + '\n';
    this.premutatuon(rezult, temp, FP_perm);
    console.log(textLog);
    return rezult
  };
  this.shift_CD_1 = function (CD) {
    for (let i = 0; i <= 55; i++)
      CD[i] = CD[i + 1];

    CD[56] = CD[28];
    CD[28] = CD[0];
  };
  this.shift_CD_2 = function (CD) {
    let C1 = CD[1];
    for (let i = 0; i <= 54; i++)
      CD[i] = CD[i + 2];
    CD[55] = CD[27];
    CD[56] = CD[28];
    CD[27] = C1;
    CD[28] = CD[0];
  };

  this.des_round = function (L, R, KeyR) {
    let E_result = new Array(49);
    let S_out = new Array(33);
    let temp_L = new Array(33);
    for (let i = 0; i < 33; i++) {
      temp_L[i] = L[i];
      L[i] = R[i];
    }
    this.premutatuon(E_result, R, E_perm);
    textLog += 'E: ' + this.printArray(E_result, 6) + '\n';
    textLog += 'KS: ' + this.printArray(KeyR, 6) + '\n';
    this.xor(E_result, KeyR);
    textLog += 'E xor KS: ' + this.printArray(E_result, 6) + '\n';
    this.split_int(S_out, 1, 4, this.do_S(S1, 1, E_result));
    this.split_int(S_out, 5, 4, this.do_S(S2, 7, E_result));
    this.split_int(S_out, 9, 4, this.do_S(S3, 13, E_result));
    this.split_int(S_out, 13, 4, this.do_S(S4, 19, E_result));
    this.split_int(S_out, 17, 4, this.do_S(S5, 25, E_result));
    this.split_int(S_out, 21, 4, this.do_S(S6, 31, E_result));
    this.split_int(S_out, 25, 4, this.do_S(S7, 37, E_result));
    this.split_int(S_out, 29, 4, this.do_S(S8, 43, E_result));
    textLog += 'Sbox: ' + this.printArray(S_out, 4) + '\n';
    this.premutatuon(R, S_out, P_perm);
    textLog += 'P: ' + this.printArray(R, 8) + '\n';
    this.xor(R, temp_L);
    textLog += 'L[i]:' + this.printArray(L, 8) + '\n';
    textLog += 'R[i]:' + this.printArray(R, 8) + '\n';
  };
  this.xor = function (a1, a2) {
    for (let i = 1; i < a1.length; i++)
      a1[i] = a1[i] ^ a2[i];
  };
  this.do_S = function (SBox, index, inbits) {
    let S_index = inbits[index] * 32 + inbits[index + 5] * 16 +
      inbits[index + 1] * 8 + inbits[index + 2] * 4 +
      inbits[index + 3] * 2 + inbits[index + 4];
    return SBox[S_index];
  };
  this.split_int = function (ary, start, bitc, val) {
    for (let j = bitc - 1; j >= 0; j--) {
      ary[start + j] = val & 1;
      val >>= 1;
    }
  };
  this.getConsoleOut = function () {
    return textLog;
  };
  this.getCript = function () {
    return rezult;
  }
  // since DES numbers bits starting at 1, we will ignore x[0]
  let IP_perm = [-1,
    58, 50, 42, 34, 26, 18, 10, 2,
    60, 52, 44, 36, 28, 20, 12, 4,
    62, 54, 46, 38, 30, 22, 14, 6,
    64, 56, 48, 40, 32, 24, 16, 8,
    57, 49, 41, 33, 25, 17, 9, 1,
    59, 51, 43, 35, 27, 19, 11, 3,
    61, 53, 45, 37, 29, 21, 13, 5,
    63, 55, 47, 39, 31, 23, 15, 7];
// final permutation (inverse initial permutation)
  let FP_perm = [-1,
    40, 8, 48, 16, 56, 24, 64, 32,
    39, 7, 47, 15, 55, 23, 63, 31,
    38, 6, 46, 14, 54, 22, 62, 30,
    37, 5, 45, 13, 53, 21, 61, 29,
    36, 4, 44, 12, 52, 20, 60, 28,
    35, 3, 43, 11, 51, 19, 59, 27,
    34, 2, 42, 10, 50, 18, 58, 26,
    33, 1, 41, 9, 49, 17, 57, 25];
// per-round expansion
  let E_perm = [-1,
    32, 1, 2, 3, 4, 5,
    4, 5, 6, 7, 8, 9,
    8, 9, 10, 11, 12, 13,
    12, 13, 14, 15, 16, 17,
    16, 17, 18, 19, 20, 21,
    20, 21, 22, 23, 24, 25,
    24, 25, 26, 27, 28, 29,
    28, 29, 30, 31, 32, 1];
// per-round permutation
  let P_perm = [-1,
    16, 7, 20, 21, 29, 12, 28, 17,
    1, 15, 23, 26, 5, 18, 31, 10,
    2, 8, 24, 14, 32, 27, 3, 9,
    19, 13, 30, 6, 22, 11, 4, 25];
// note we do use element 0 in the S-Boxes
  let S1 = [14, 4, 13, 1, 2, 15, 11, 8, 3, 10, 6, 12, 5, 9, 0, 7,
    0, 15, 7, 4, 14, 2, 13, 1, 10, 6, 12, 11, 9, 5, 3, 8,
    4, 1, 14, 8, 13, 6, 2, 11, 15, 12, 9, 7, 3, 10, 5, 0,
    15, 12, 8, 2, 4, 9, 1, 7, 5, 11, 3, 14, 10, 0, 6, 13];
  let S2 = [15, 1, 8, 14, 6, 11, 3, 4, 9, 7, 2, 13, 12, 0, 5, 10,
    3, 13, 4, 7, 15, 2, 8, 14, 12, 0, 1, 10, 6, 9, 11, 5,
    0, 14, 7, 11, 10, 4, 13, 1, 5, 8, 12, 6, 9, 3, 2, 15,
    13, 8, 10, 1, 3, 15, 4, 2, 11, 6, 7, 12, 0, 5, 14, 9];
  let S3 = [10, 0, 9, 14, 6, 3, 15, 5, 1, 13, 12, 7, 11, 4, 2, 8,
    13, 7, 0, 9, 3, 4, 6, 10, 2, 8, 5, 14, 12, 11, 15, 1,
    13, 6, 4, 9, 8, 15, 3, 0, 11, 1, 2, 12, 5, 10, 14, 7,
    1, 10, 13, 0, 6, 9, 8, 7, 4, 15, 14, 3, 11, 5, 2, 12];
  let S4 = [7, 13, 14, 3, 0, 6, 9, 10, 1, 2, 8, 5, 11, 12, 4, 15,
    13, 8, 11, 5, 6, 15, 0, 3, 4, 7, 2, 12, 1, 10, 14, 9,
    10, 6, 9, 0, 12, 11, 7, 13, 15, 1, 3, 14, 5, 2, 8, 4,
    3, 15, 0, 6, 10, 1, 13, 8, 9, 4, 5, 11, 12, 7, 2, 14];
  let S5 = [2, 12, 4, 1, 7, 10, 11, 6, 8, 5, 3, 15, 13, 0, 14, 9,
    14, 11, 2, 12, 4, 7, 13, 1, 5, 0, 15, 10, 3, 9, 8, 6,
    4, 2, 1, 11, 10, 13, 7, 8, 15, 9, 12, 5, 6, 3, 0, 14,
    11, 8, 12, 7, 1, 14, 2, 13, 6, 15, 0, 9, 10, 4, 5, 3];
  let S6 = [12, 1, 10, 15, 9, 2, 6, 8, 0, 13, 3, 4, 14, 7, 5, 11,
    10, 15, 4, 2, 7, 12, 9, 5, 6, 1, 13, 14, 0, 11, 3, 8,
    9, 14, 15, 5, 2, 8, 12, 3, 7, 0, 4, 10, 1, 13, 11, 6,
    4, 3, 2, 12, 9, 5, 15, 10, 11, 14, 1, 7, 6, 0, 8, 13];
  let S7 = [4, 11, 2, 14, 15, 0, 8, 13, 3, 12, 9, 7, 5, 10, 6, 1,
    13, 0, 11, 7, 4, 9, 1, 10, 14, 3, 5, 12, 2, 15, 8, 6,
    1, 4, 11, 13, 12, 3, 7, 14, 10, 15, 6, 8, 0, 5, 9, 2,
    6, 11, 13, 8, 1, 4, 10, 7, 9, 5, 0, 15, 14, 2, 3, 12];
  let S8 = [13, 2, 8, 4, 6, 15, 11, 1, 10, 9, 3, 14, 5, 0, 12, 7,
    1, 15, 13, 8, 10, 3, 7, 4, 12, 5, 6, 11, 0, 14, 9, 2,
    7, 11, 4, 1, 9, 12, 14, 2, 0, 6, 10, 13, 15, 3, 5, 8,
    2, 1, 14, 7, 4, 10, 8, 13, 15, 12, 9, 0, 3, 5, 6, 11];

//, first, key, permutation
  let PC_1_perm = [-1,
    // C subkey bits
    57, 49, 41, 33, 25, 17, 9, 1, 58, 50, 42, 34, 26, 18,
    10, 2, 59, 51, 43, 35, 27, 19, 11, 3, 60, 52, 44, 36,
    // D subkey bits
    63, 55, 47, 39, 31, 23, 15, 7, 62, 54, 46, 38, 30, 22,
    14, 6, 61, 53, 45, 37, 29, 21, 13, 5, 28, 20, 12, 4];

//, per-round, key, selection, permutation
  let PC_2_perm = [-1,
    14, 17, 11, 24, 1, 5, 3, 28, 15, 6, 21, 10,
    23, 19, 12, 4, 26, 8, 16, 7, 27, 20, 13, 2,
    41, 52, 31, 37, 47, 55, 30, 40, 51, 45, 33, 48,
    44, 49, 39, 56, 34, 53, 46, 42, 50, 36, 29, 32];
  this.init();
}
