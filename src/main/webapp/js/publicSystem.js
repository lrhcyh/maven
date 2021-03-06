/**
 * 将form表单中带name属性的dom节点系列化成object xz
 */
var serializeObject = function(form) {
	var o = {};
	$.each(form.serializeArray(), function(index) {
		if (o[this["name"]]) {
			o[this["name"]] = o[this["name"]] + "," + this["value"];
		} else {
			o[this["name"]] = this["value"];
		}
	});
	return o;
};

/**
 * 字符串转时间
 */
var stringToDate = function(DateStr) {
	var converted = Date.parse(DateStr);
	var myDate = new Date(converted);
	if (isNaN(myDate)) {
		var arys = DateStr.split('-');
		myDate = new Date(arys[0], --arys[1], arys[2]);
	}
	return myDate;
};

$.extend($.fn.validatebox.defaults.rules, {
	phoneAndMobile : {
		validator : function(value) {
			return (/^(13|15|18)\d{9}$/i.test(value)) || (/^((\(\d{2,3}\))|(\d{3}\-))?(\(0\d{2,3}\)|0\d{2,3}-)?[1-9]\d{6,7}(\-\d{1,4})?$/i.test(value));
		},
		message : '联系方式格式不正确,座机格式:020-88888888,手机格式:13888888888'
	},
	money : {
		// /^(?:\d+|\d{1,3})(?:\.\d{1,2})?$/
		validator : function(value, param) {
			return /^(?:\d+|\d{1,3})(?:\.\d{1,2})?$/i.test(value);
		},
		message : "请填写正确的价格"
	},
	num : {// 验证是否为数字,如果是数字则返回true
		validator : function(value, param) {
			return !isNaN(value);
		},
		message : "请填写数字"
	},
	idcard : {// 验证身份证
		validator : function(value) {
			return /^\d{15}(\d{2}[A-Za-z0-9])?$/i.test(value);
		},
		message : '身份证号码格式不正确'
	},

	email : {// 邮箱
		validator : function(value) {
			return /^(\w-*\.*)+@(\w-?)+(\.\w{2,})+$/i.test(value);
		},
		message : '邮箱格式不正确'
	},
	phone : {// 验证电话号码
		validator : function(value) {
			return /^((\(\d{2,3}\))|(\d{3}\-))?(\(0\d{2,3}\)|0\d{2,3}-)?[1-9]\d{6,7}(\-\d{1,4})?$/i.test(value);
		},
		message : '格式不正确,请使用下面格式:020-88888888'
	},
	mobile : {// 验证手机号码
		validator : function(value) {
			return /^(13|15|17|18)\d{9}$/i.test(value);
		},
		message : '手机号码格式不正确'
	},
	mobileoremail : {// 验证手机号码邮箱
		validator : function(value) {
			if (/^(13|15|18)\d{9}$/i.test(value)) {
				return true;
			}
			if (/^(\w-*\.*)+@(\w-?)+(\.\w{2,})+$/i.test(value)) {
				return true;
			}
			return false;
		},
		message : '手机或邮箱格式不正确'
	},
	msn : {
		validator : function(value) {
			return /^\w+([-+.]\w+)*@\w+([-.]\w+)*\.\w+([-.]\w+)*$/.test(value);
		},
		message : '请输入有效的msn账号(例：abc@hotnail(msn/live).com)'
	},

	num : {// 验证是否为数字,如果是数字则返回true
		validator : function(value, param) {
			return !isNaN(value);
		},
		message : "请填写数字"
	},
	idcard : {// 验证身份证
		validator : function(value) {
			return /^\d{15}(\d{2}[A-Za-z0-9])?$/i.test(value);
		},
		message : '身份证号码格式不正确'
	},
	phone : {// 验证电话号码
		validator : function(value) {
			return /^((\(\d{2,3}\))|(\d{3}\-))?(\(0\d{2,3}\)|0\d{2,3}-)?[1-9]\d{6,7}(\-\d{1,4})?$/i.test(value);
		},
		message : '格式不正确,请使用下面格式:020-88888888'
	},
	mobile : {// 验证手机号码
		validator : function(value) {
			return /^(13|15|18)\d{9}$/i.test(value);
		},
		message : '手机号码格式不正确'
	},
	msn : {
		validator : function(value) {
			return /^\w+([-+.]\w+)*@\w+([-.]\w+)*\.\w+([-.]\w+)*$/.test(value);
		},
		message : '请输入有效的msn账号(例：abc@hotnail(msn/live).com)'
	},
	loginName : {
		validator : function(value) {

			var s = "";
			$.ajax({
				url : 'admin/checkNameOnly.htm',
				data : {
					loginName : value
				},
				type : 'POST',
				async : false,
				success : function(data) {
					var a = $.parseJSON(data);
					s = a.rows;
				}
			});
			if (s == "unexist") {
				return true;
			} else
				return false;
		},
		message : '该用户名已被占使用'
	},

	checkAssetQuantity : {
		validator : function(value) {
			var quantity = $("#quantity_valid").val();
			if (parseInt(quantity) < parseInt(value) || value == "0") {
				return false;
			} else {
				return true;
			}
		},
		message : '资产数量必须大于0,小于或等于最大值'
	},

	attendanceStartTime : {
		validator : function(value) {
			var d1 = dateUtil.getCurrentDateStr_1() + " " + $("#attendanceStartTime").val();
			var d2 = dateUtil.getCurrentDateStr_1() + " " + $("#attendanceStopTime").val();

			d1 = new Date(d1).getTime();
			d2 = new Date(d2).getTime();
			if (d1 < d2) {
				return true;
			} else
				return false;
		},
		message : '开始时间不能大于结束时间'
	},
	attendanceStopTime : {
		validator : function(value) {
			var d1 = dateUtil.getCurrentDateStr_1() + " " + $("#attendanceStartTime").val();
			var d2 = dateUtil.getCurrentDateStr_1() + " " + $("#attendanceStopTime").val();

			d1 = new Date(d1).getTime();
			d2 = new Date(d2).getTime();
			// alert(d1+"--"+d2);
			if (d2 > d1) {
				return true;
			} else
				return false;
		},
		message : '结束时间必须大于开始时间'
	},
	checkProductNum : {
		validator : function(value) {
			var result = "";
			$.ajax({
				url : $("base")[0].href + 'admin/product/checkProductNum.htm',
				data : {
					num : value
				},
				type : 'POST',
				async : false,
				success : function(data) {
					var a = $.parseJSON(data);
					result = a.rel;
				}
			});
			if (result == "unexist") {
				return true;
			} else {
				return false;
			}
		},
		message : '您输入的编号长度超过20或已存在'
	},
	checkActivityName : {
		validator : function(value) {
			var s = "";
			$.ajax({
				url : 'couponactivity/checkActivityName.form',
				data : {
					name : value
				},
				type : 'POST',
				async : false,
				success : function(j) {
					/*
					 * var a = $.parseJSON(result); alert(result);
					 */
					s = j.result;
				}
			});
			if (s == "unexist") {
				return true;
			} else
				return false;
		},
		message : '该活动名已被占用'
	},
	checkActivityCode : {
		validator : function(value) {
			var s = "";
			$.ajax({
				url : 'checkCoupon/findStartCode.form',
				data : {
					code : value
				},
				type : 'POST',
				async : false,
				success : function(j) {
					s = j;
				}
			});

			if (s == 0) {
				return true;
			} else
				return false;
		},
		message : '该起始编号以存在，请重新输入！'
	},
	rookieIsExist : {
		validator : function(value) {
			var s = "";
			$.ajax({
				url : 'couponactivity/rookieIsExist.form',
				type : 'POST',
				async : false,
				success : function(j) {
					s = j.result;
				}
			});

			if (s == 'exist' && !$('#isRookie').is(':checked')) {
				return true;
			} else if (s == 'unexist') {
				return true;
			} else
				return false;
		},
		message : '已经有审核通过的新手活动'
	},
	phoneNumberIsExist : {
		validator : function(value) {
			var s = "";
			$.ajax({
				url : 'vip/phoneNumberIsExist.form',
				type : 'POST',
				data : {
					phone : value
				},
				async : false,
				success : function(j) {
					s = j;
				}
			});

			if (s == 0) {
				return false;
			} else {
				return true;
			}
		},
		message : '用户不存在'
	},
	phoneNumberIsRegist : {
		validator : function(value) {
			var s = "";
			$.ajax({
				url : 'vip/phoneNumberIsExist.form',
				type : 'POST',
				data : {
					phone : value
				},
				async : false,
				success : function(j) {
					s = j;
				}
			});

			if (s == 0) {
				return false;
			} else {
				return true;
			}
		},
		message : '该手机号非平台注册号码'
	},
	referenceCondition : {
		validator : function(value) {
			var s = "";
			$.ajax({
				url : 'vip/referenceCondition.form',
				type : 'POST',
				data : {
					phone : value
				},
				async : false,
				success : function(j) {
					s = j;
				}
			});

			if (s == 5) {
				return false;
			} else {
				return true;
			}
		},
		message : '该用户推荐人数已达活动上限'
	},
	isGroupUser : {
		validator : function(value, param) {
			if (value != $(param[0]).val()) {
				var s = '';
				$.ajax({
					url : 'vip/isGroupUser.form',
					type : 'POST',
					data : {
						phone : value
					},
					async : false,
					success : function(j) {
						s = j;
					}
				});
				if (s == 0) {
					return false;
				}
			}
			return true;
		},
		message : '该用户暂无推荐权限'
	}
});

$.extend($.fn.numberbox.defaults.rules, {
	validDiscount : {// 扩展数字验证折扣和提成(0-10)
		validator : function(value, param) {
			return (value < 10 && value >= 0);
		},
		message : "请填写0-10之间的数字"
	},
	validNumber : {// 扩展数字验证(大于0)
		validator : function(value, param) {
			// console.info(Number(value));
			var array = value.split(",");
			var temp = "";
			for (var i = 0; i < array.length; i++) {// 去逗号验证
				temp = temp + "" + array[i];
			}
			return (Number(temp) > 0);
		},
		message : "数值必须大于0"
	},
	validNum : {// 扩展数字验证(大于等于0)
		validator : function(value, param) {
			// console.info(Number(value));
			var array = value.split(",");
			var temp = "";
			for (var i = 0; i < array.length; i++) {// 去逗号验证
				temp = temp + "" + array[i];
			}
			return (Number(temp) >= 0);
		},
		message : "数值必须大于或等于0"
	},
	validCount : {
		validator : function(value, param) {
			var maxValue = $(param[0]).numberbox('getValue');
			return parseInt(value) >= 0 && parseInt(value) <= parseInt(maxValue);
		},
		message : "数值必须大于0,小于等于级别截至值"
	},
	checkNum : {
		validator : function(value) {
			var s = "";
			if (value.length > 30) {
				return false;
			}
			$.ajax({
				url : 'admin/product/checkProductNum.htm',
				data : {
					num : value
				},
				type : 'POST',
				async : false,
				success : function(data) {
					var a = $.parseJSON(data);
					s = a.rel;
				}
			});
			if (s == "unexist") {
				return true;
			} else
				return false;
		},
		message : '该用户名已被占使用或超过30个字符'
	},
	compareDate : {
		validator : function(value, param) {
			startTime2 = $(param[0]).datetimebox('getValue');
			var d1 = $.fn.datebox.defaults.parser(startTime2);
			var d2 = $.fn.datebox.defaults.parser(value);
			varify = d2 > d1;
			return varify;
		},
		message : '开始日期不能大于结束日期'
	},
	equals : {
		validator : function(value, param) {
			return value == $(param[0]).val();
		},
		message : '两次密码输入不一致'
	}
});

$.extend($.fn.combobox.defaults.rules, {
	validCombobox : {
		validator : function(value, param) {
			return value != "--请选择--";
		},
		message : "请选择"
	}
});

/**
 * 将毫秒的时间转成时间字符串
 * 
 * @param mmSecond
 * @param length
 * @returns {String}
 */
var getTimeTxt = function(mmSecond, length) {
	var t = new Date(mmSecond);
	var r = t.getFullYear() + "-";
	r += (t.getMonth() + 1 >= 10 ? t.getMonth() + 1 : "0" + (t.getMonth() + 1));
	r += "-" + (t.getDate() >= 10 ? t.getDate() : "0" + t.getDate());
	if (length == 10) {
		return r;
	}
	r += " " + (t.getHours() >= 10 ? t.getHours() : "0" + t.getHours());
	r += ":" + (t.getMinutes() >= 10 ? t.getMinutes() : "0" + t.getMinutes());

	if (length == 16) {
		return r;
	}

	r += ":" + (t.getSeconds() >= 10 ? t.getSeconds() : "0" + t.getSeconds());
	if (length == 19) {
		return r;
	}
}

/**
 * xz 2014-03-29 防止移动dialog时超出边界
 */
var easyuiPanelOnMove = function(left, top) {
	if (left < 1) {
		left = 1;
	}
	if (top < 1) {
		top = 1;
	}
	var width = parseInt($(this).parent().css('width')) + 14;
	var height = parseInt($(this).parent().css('height')) + 14;
	var right = left + width;
	var buttom = top + height;
	var browserWidth = $(window).width();
	var browserHeight = $(window).height();
	if (right > browserWidth) {
		left = browserWidth - width;
	}
	if (buttom > browserHeight) {
		top = browserHeight - height;
	}
	$(this).parent().css({/* 修正面板位置 */
		left : left,
		top : top
	});
};

$.fn.dialog.defaults.onMove = easyuiPanelOnMove;

/*
 * 为grid增加tooltip提示功能
 * 使用方法1:(所有列),$("#dg").datagrid("tooltip");2:(指定列),$("#dg").datagrid("tooltip",["name","age"])
 */
var gridTooltipOptions = {
	tooltip : function(jq, fields) {
		return jq.each(function() {
			var panel = $(this).datagrid('getPanel');
			if (fields && typeof fields == 'object' && fields.sort) {
				$.each(fields, function() {
					var field = this;
					bindEvent($('.datagrid-body td[field=' + field + '] .datagrid-cell', panel));
				});
			} else {
				bindEvent($(".datagrid-body .datagrid-cell", panel));
			}
		});

		function bindEvent(jqs) {
			jqs.mouseover(function() {
				var content = $(this).text();
				if (content.replace(/(^\s*)|(\s*$)/g, '').length > 0) {
					$(this).tooltip({
						content : content,
						trackMouse : true,
						position : 'bottom',
						onHide : function() {
							$(this).tooltip('destroy');
						},
						onUpdate : function(p) {
							var tip = $(this).tooltip('tip');
							if (parseInt(tip.css('width')) > 500) {
								tip.css('width', 500);
							}
						}
					}).tooltip('show');
				}
			});
		}
	}
};

$.extend($.fn.datagrid.methods, gridTooltipOptions);
$.extend($.fn.treegrid.methods, gridTooltipOptions);

/*
 * 扩展datagrid列editor的datebox类型使用my97日历控件
 */
/*
 * $.extend($.fn.datagrid.defaults.editors, { datebox : { init :
 * function(container, options) { var input = $("<input type='text'
 * class='Wdate' readonly
 * onfocus='WdatePicker({dateFmt:\"yyyy-MM-dd\"})'>").appendTo(container);
 * input.validatebox(options); return input; }, destroy : function(target) {
 * $(target).remove(); }, getValue : function(target) { return $(target).val(); },
 * setValue : function(target, value) { $(target).val(value); }, resize :
 * function(target, width) { $(target)._outerWidth(width); } } });
 */

/**
 * js检查图片格式方法
 * 
 * @param file
 *            文件名
 * @returns
 */
function checkPictureType(file) {
	// 为了避免转义反斜杠出问题，这里将对其进行转换
	var re = /(\\+)/g;
	var filename = file.replace(re, "#");
	// 对路径字符串进行剪切截取
	var one = filename.split("#");
	// 获取数组中最后一个，即文件名
	var two = one[one.length - 1];
	// 再对文件名进行截取，以取得后缀名
	var three = two.split(".");
	// 获取截取的最后一个字符串，即为后缀名
	var last = three[three.length - 1];
	// 添加需要判断的后缀名类型
	var tp = "jpg,gif,bmp,JPG,GIF,BMP,PNG,png";
	// 返回符合条件的后缀名在字符串中的位置
	var rs = tp.indexOf(last);
	// 如果返回的结果大于或等于0，说明包含允许上传的文件类型
	return rs;
}

/**
 * js检查excel方法
 * 
 * @param file
 *            文件名
 * @returns
 */
function checkExcelType(file) {
	// 为了避免转义反斜杠出问题，这里将对其进行转换
	var re = /(\\+)/g;
	var filename = file.replace(re, "#");
	// 对路径字符串进行剪切截取
	var one = filename.split("#");
	// 获取数组中最后一个，即文件名
	var two = one[one.length - 1];
	// 再对文件名进行截取，以取得后缀名
	var three = two.split(".");
	// 获取截取的最后一个字符串，即为后缀名
	var last = three[three.length - 1];
	// 添加需要判断的后缀名类型
	var tp = "xls,xlsx,XLS,XLSX";
	// 返回符合条件的后缀名在字符串中的位置
	var rs = tp.indexOf(last);
	// 如果返回的结果大于或等于0，说明包含允许上传的文件类型
	return rs;
}

$.extend($.fn.datagrid.defaults.editors, {
	numberspinner : {
		init : function(container, options) {
			var input = $('<input type="text">').appendTo(container);
			return input.numberspinner(options);
		},
		destroy : function(target) {
			$(target).numberspinner('destroy');
		},
		getValue : function(target) {
			return $(target).numberspinner('getValue');
		},
		setValue : function(target, value) {
			$(target).numberspinner('setValue', value);
		},
		resize : function(target, width) {
			$(target).numberspinner('resize', width);
		}
	}
});

$.extend($.fn.tree.methods, {
	getLevel : function(jq, target) {
		var l = $(target).parentsUntil("ul.tree", "ul");
		return l.length + 1;
	}
});


$.extend($.fn.datagrid.methods, {
    autoMergeCells: function (jq, fields) {
        return jq.each(function () {
            var target = $(this);
            if (!fields) {
                fields = target.datagrid("getColumnFields");
            }
            var rows = target.datagrid("getRows");
            var i = 0,
            j = 0,
            temp = {};
            for (i; i < rows.length; i++) {
                var row = rows[i];
                j = 0;
                for (j; j < fields.length; j++) {
                    var field = fields[j];
                    var tf = temp[field];
                    if (!tf) {
                        tf = temp[field] = {};
                        tf[row[field]] = [i];
                    } else {
                        var tfv = tf[row[field]];
                        if (tfv) {
                            tfv.push(i);
                        } else {
                            tfv = tf[row[field]] = [i];
                        }
                    }
                }
            }
            $.each(temp, function (field, colunm) {
                $.each(colunm, function () {
                    var group = this;
 
                    if (group.length > 1) {
                        var before,
                        after,
                        megerIndex = group[0];
                        for (var i = 0; i < group.length; i++) {
                            before = group[i];
                            after = group[i + 1];
                            if (after && (after - before) == 1) {
                                continue;
                            }
                            var rowspan = before - megerIndex + 1;
                            if (rowspan > 1) {
                                target.datagrid('mergeCells', {
                                    index: megerIndex,
                                    field: field,
                                    rowspan: rowspan
                                });
                            }
                            if (after && (after - before) != 1) {
                                megerIndex = after;
                            }
                        }
                    }
                });
            });
        });
    }
});

