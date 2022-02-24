		function XmlParser(buttonId, charge) {
			var self = this, workbook = null, nameWorkbook;
			buttonId = $(buttonId);
			this.parseJsonToArray = function (Json) {
				var rowKey = Object.keys(Json), out = [];
				for (var g = 0; g < rowKey.length; g++) {
					var collKey = Object.keys(Json[rowKey[g]]);
					out[g] = [];
					for (var j = 0; j < collKey.length; j++) {
						out[g][j] = Json[rowKey[g]][collKey[j]]
					}
				}
				return out;
			};
			this.parseArrayToAssoc = function (Array) {
				var name = {}, nameMass = [], g, j, out = [];
				for (g = 1; g < Array[0].length; g++) {
					name[Array[0][g]] = {};
					nameMass[g] = Array[0][g];
				}
				for (g = 1; g < Array.length; g++) {
					if (Array[g].length <= 2) {
						continue;
					}
					name[Array[g][0]] = {};
					nameMass[g] = Array[g][0];
				}
				for (g = 1; g < Array.length; g++) {
					if (typeof Array[g][2] === "string") {
						Array = Array.slice(g);
						out = self.parseArrayToAssoc(Array);
						out.push(name);
						return out;
					}
					if (Array[g].length === 1) {
						continue;
					}
					for (j = 1; j < Array[g].length; j++) {
						try {
							name[Array[g][0]][nameMass[j]] = Array[g][j];
						} catch (e) {
						}
					}
				}
				out.push(name);
				return out;
			};
			this.loadData = function (e) {
				var reader = new FileReader();
				reader.onload = function (event) {
					var data = new Uint8Array(event.target.result);
					var Sheets = XLSX.read(data, {type: 'array'})
					workbook = Sheets.Sheets;
					nameWorkbook = Sheets.SheetNames;
					charge(nameWorkbook);
				}
				reader.readAsArrayBuffer(e.target.files[0]);
			};
			this.getDataWorkbook = function (nameWorkbook) {
				if (workbook == null) {
					return false;
				}
				return workbook[nameWorkbook];
			};
			this.getSheets = function () {

			};
			this.getParseWorkbook = function (nameWorkbook) {
				var workbook = self.getDataWorkbook(nameWorkbook);
				if (!workbook) {
					return false;
				}
				workbook = self.parseJsonToArray(XLSX.utils.sheet_to_json(workbook));
				return self.parseArrayToAssoc(workbook);
			};
			buttonId.on('change', self.loadData);
			buttonId.on('click', function () {
				buttonId[0].value = "";
			});
			return self;
		}

		function update(nameWb) {
			$("#names").empty();
			for (var g = 0; g < nameWb.length; g++) {
				$("#names").append($("<div style='margin: 2px; border: 1px solid; cursor: pointer;'>" + nameWb[g] + "</div>").on('click', (function () {
					var name = nameWb[g];
					return function () {
						var data = xmlControl.getParseWorkbook(name);
						var text = "";
						$("#excel_out").empty();
						for (var k = 0; k < data.length; k++) {
							text += 'Таблица №' + (k + 1) + "</br>" + getHtml(data[k]);
						}
						$("#excel_out").append(text);
					}
				})()))
			}

			function getHtml(data) {
				var text = "";
				var key = Object.keys(data);
				for (g = 0; g < key.length; g++) {
					var keyRow = Object.keys(data[key[g]]);
					for (var j = 0; j < keyRow.length; j++) {
						text += "" + key[g] + " - " + keyRow[j] + " = " + data[key[g]][keyRow[j]] + "</br>";
					}
				}
				return text;
			}
		}

		var xmlControl = new XmlParser("#excel_input", update);