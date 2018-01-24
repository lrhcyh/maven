
    if (!window.utl) {
        window.utl = {};
    }

    String.prototype.format2 = function () {
        var length = arguments.length;
        var reg;
        var s = this;
        for (var i = 0; i < length; i++) {
            reg = new RegExp('([{]' + i + '[}])', 'gm');
            s = s.replace(reg, arguments[i]);
            reg.lastIndex = 0;
        }
        return s;
    }

    String.prototype.trim2 = function () {
        if (arguments.length > 0) {
            var s = arguments[0];
            var reg = new RegExp('(^[' + s + ']*)|([' + s + ']*$)', 'gm');
            return this.replace(reg, '');
        }
        return this.replace(/(^\s*)|(\s*$)/g, '');
    }

    function dateFormat(dateString,format) {
        if(!dateString)return "";
        var time = new Date(dateString.replace(/-/g,'/').replace(/T|Z/g,' ').trim());
        var o = {
            "M+": time.getMonth() + 1, //月份
            "d+": time.getDate(), //日
            "h+": time.getHours(), //小时
            "m+": time.getMinutes(), //分
            "s+": time.getSeconds(), //秒
            "q+": Math.floor((time.getMonth() + 3) / 3), //季度
            "S": time.getMilliseconds() //毫秒
        };
        if (/(y+)/.test(format)) format = format.replace(RegExp.$1, (time.getFullYear() + "").substr(4 - RegExp.$1.length));
        for (var k in o)
            if (new RegExp("(" + k + ")").test(format)) format = format.replace(RegExp.$1, (RegExp.$1.length == 1) ? (o[k]) : (("00" + o[k]).substr(("" + o[k]).length)));
        return format;
    }

    utl.throttle = function(delay, action){
        var last = 0;
        return function(){
            var curr = +new Date();
            if (curr - last > delay)
                action.apply(this, arguments);
            last = curr;
        };
    };

    utl.debounce = function( action,idle){
        var last;
        return function(){
            var ctx = this, args = arguments;
            clearTimeout(last);
            last = setTimeout(function(){
                action.apply(ctx, args);
            }, idle);
        };
    };

    /////begin ui helper
    utl.getScrollBarWidth =  function () {
        // Get the scrollbar width
        var scrollDiv = document.createElement("div");
        scrollDiv.className = "scrollbar-measure";
        document.body.appendChild(scrollDiv);
        SCROLLBARWIDTH = scrollDiv.offsetWidth - scrollDiv.clientWidth;
        // Delete the DIV
        document.body.removeChild(scrollDiv);
        return SCROLLBARWIDTH;
    }
    utl.successMessage =  '操作成功!'
    utl.warningMessage =  '警告!'
    utl.failMessage = '操作失败!'
    utl.noticeMessage = '温馨提示!'
    utl.loadingMessage =  '加载中'+'<span class="dotting"></span>'
    utl.messageType =  { success: 1, warning: 2, fail: 3 }
    utl.showMessage =  function (messageType, message, duration, showClose) {
        //if not pass isMask, default is no mask
        if ($(".hx_messagebox").length != 0)
            $(".hx_messagebox").remove();

        var message_box = $("<div class='hx_messagebox'><span></span></div>");
        message_box.appendTo('body');

        switch (messageType) {

            case 2:
            {
                if (message == '' || message == undefined)
                    message_box.find('span').html(utl.warningMessage);
                else
                    message_box.find('span').html(message);
                message_box.addClass("warning_box");
                break;
            }
            case 3:
            {
                if (message == '' || message == undefined)
                    message_box.find('span').html(utl.failMessage);
                else
                    message_box.find('span').html(message);
                message_box.addClass("fail_box");
                break;
            }
            case 4:
            {
                if (message == '' || message == undefined)
                    message_box.find('span').html(utl.noticeMessage);
                else
                    message_box.find('span').html(message);
                message_box.addClass("notice_box");
                break;
            }
            case 1:
            default:
            {
                if (message == '' || message == undefined)
                    message_box.find('span').html(utl.successMessage);
                else
                    message_box.find('span').html(message);
                message_box.addClass("success_box");
                break;
            }
        }
        if (message_box.width() > 500) {
            message_box.css({ "font-size": "12px" }).width(500);
        }
        message_box.css("marginLeft", -message_box.outerWidth() / 2);
        message_box.css("marginTop", -message_box.outerHeight() / 2);
        message_box.fadeIn(100);
        message_box.animate({ top: "50%" }, 500);
        if (duration == '' || duration == undefined)
            duration = 3000;

        if (showClose == '' || showClose == undefined)
            setTimeout(utl.hideMessage, duration);
        else {
            var $close = $('<div class="close"><a href="javascript:void 0;" title="close">&times;</a></div>');
            message_box.append($close);
            $close.off("click").on('click', function () {
                utl.hideMessage();
            });
        }
    }
    utl.hideMessage = function () {
        if ($(".hx_messagebox").length != 0) {
            $(".hx_messagebox").fadeOut(500, function () {
                $(".hx_messagebox").remove();
            });

        }

    }
    utl.getParam = function(){
        var url = location.search; //获取url中"?"符后的字串

        var theRequest = new Object();
        if (url.indexOf("?") != -1) {
            var str = url.substr(1);
            strs = str.split("&");
            for(var i = 0; i < strs.length; i ++) {
                theRequest[strs[i].split("=")[0]]=(strs[i].split("=")[1]);
            }
        }

        return theRequest;
    }
    utl.getQueryString = function (key) {
        key = key.replace(/[\[]/, "\\[").replace(/[\]]/, "\\]");
        var regex = new RegExp("[\\?&]" + key + "=([^&#]*)"),
            results = regex.exec(location.search);
        return results == null ? "" : decodeURIComponent(results[1].replace(/\+/g, " "));
    }
    utl.isVisible =function ($el) {
        if ($el.length == 0) return false;
        return !($el.css('display') == 'none' || $el.get(0).offsetHeight == 0 || $el.get(0).offsetWidth == 0);

    }
    utl.getCss = function (cssAttribute, $e) {
        var v = Math.ceil(parseFloat($e.css(cssAttribute)));
        if (isNaN(v)) {
            return 0;
        }
        return v;
    }
    utl.isPointInRectByPosition =function (x, y, $rect) {
        var leftTop = $rect.position();
        var rightBottom = { left: leftTop.left + $rect.width(), top: leftTop.top + $rect.height() };
        if (leftTop.left <= x && rightBottom.left >= x && leftTop.top <= y && rightBottom.top >= y) {
            return true;
        }
        return false;
    }

    //用promise模式重写jsonpRequest
    utl.jsonpPromise = function(url, data){
        if(RSVP==undefined){
            alert("请先引入RSVP.js");
            return;
        }

        return new RSVP.Promise(function(resolve, reject){
            if(null == window.fwJsonpCallbackId){
                window.fwJsonpCallbackId = 0;
            }
            if(null == window.fwJsonpData){
                window.fwJsonpData = {};
            }

            var IE_VERSION;
            try{
                IE_VERSION = +navigator.userAgent.match(/MSIE ([\d.]+)?/)[1];
            }catch(ex){
                IE_VERSION = undefined;
            }


            var  jsonpScript = document.createElement("script");

            if(IE_VERSION != undefined && IE_VERSION <= 8 ){
                jsonpScript.onreadystatechange = function(){
                    if('complete' == this.readyState ||'loaded' == this.readyState){
                        jsonpScript.onreadystatechange = null;
                        if(null == window.fwJsonpData[""+curFwJsonpCallbackId]){
                            reject("获取数据失败");
                        }else{
                            resolve(window.fwJsonpData[""+curFwJsonpCallbackId]);
                        }
                        delete window.fwJsonpData[""+curFwJsonpCallbackId];
                        jsonpScript.onreadystatechange = null;
                        document.getElementsByTagName("head")[0].removeChild(jsonpScript);
                    }
                }
            }
            else{
                jsonpScript.onload = function(){

                    if(null == window.fwJsonpData[""+curFwJsonpCallbackId]){
                        reject("获取数据失败");
                    }else{
                        resolve(window.fwJsonpData[""+curFwJsonpCallbackId]);
                    }
                    //删除全局变量的数据
                    delete window.fwJsonpData[""+curFwJsonpCallbackId];
                    jsonpScript.onreadystatechange = null;
                    document.getElementsByTagName("head")[0].removeChild(jsonpScript);
                }

                //onerror事件,标准浏览器支持，IE6,7,8,OPERA不支持，
                var supportScriptError = (!window.opera) && -[1,];
                if(supportScriptError){
                    jsonpScript.onerror =function(){
                        //删除全局变量的数据
                        delete window.fwJsonpData[""+curFwJsonpCallbackId];
                        jsonpScript.onreadystatechange = null;
                        document.getElementsByTagName("head")[0].removeChild(jsonpScript);
                    }
                }

            }

            //在url末尾加上参数
            var query = "";
            for(var key in data){
                query +=  '&'  + key + "=" + encodeURIComponent(data[key]);
            }
            var curFwJsonpCallbackId = ++window.fwJsonpCallbackId;
            query += "&callback="+"fwJsonpCallback"+(curFwJsonpCallbackId);
            query += "&t=" + new Date().getTime();
            url = url + ( url.indexOf('?') > -1 ? '&' : '?') + query.substring(1);
            //将服务器返回的JSON数据放到全局变量
            window["fwJsonpCallback"+curFwJsonpCallbackId] = function(data){
                window.fwJsonpData[""+curFwJsonpCallbackId] = data;
            };
            jsonpScript.src = url;
            document.getElementsByTagName("head")[0].appendChild(jsonpScript);




        });

    }

    //------------------------------
    utl.jsonpRequest =  function (url,data,func){
        if('[object Function]' != Object.prototype.toString.call(func)){
            func = function(){};
        }

        if(null == window.fwJsonpCallbackId){
            window.fwJsonpCallbackId = 0;
        }
        if(null == window.fwJsonpData){
            window.fwJsonpData = {};
        }
        var jsonpScript = document.createElement("script");

        var IE_VERSION;
        try{
            IE_VERSION = +navigator.userAgent.match(/MSIE ([\d.]+)?/)[1];
        }catch(ex){
            IE_VERSION = undefined;
        }
        //jsonp回调函数
        //下面这个判断条件是不是可以改为?if (script.readyState) { //IE?

        if(IE_VERSION != undefined && IE_VERSION <= 8 ){
            jsonpScript.onreadystatechange = function(){
                if('complete' == this.readyState ||'loaded' == this.readyState){
                    this.onreadystatechange = null;
                    if(null == window.fwJsonpData[""+curFwJsonpCallbackId]){
                      //  utl.showMessage(utl.messageType.fail,"获取数据失败！",2000);
                    }else{
                        func(window.fwJsonpData[""+curFwJsonpCallbackId]);
                    }
                    delete window.fwJsonpData[""+curFwJsonpCallbackId];
                    jsonpScript.onreadystatechange = null;
                    document.getElementsByTagName("head")[0].removeChild(jsonpScript);
                }
            }
        }
        else{
            jsonpScript.onload = function(){
                if(null == window.fwJsonpData[""+curFwJsonpCallbackId]){
                   // utl.showMessage(utl.messageType.fail,"获取数据失败！",2000);
                }else{
                    func(window.fwJsonpData[""+curFwJsonpCallbackId]);
                }
                //删除全局变量的数据
                delete window.fwJsonpData[""+curFwJsonpCallbackId];
                jsonpScript.onreadystatechange = null;
                document.getElementsByTagName("head")[0].removeChild(jsonpScript);
            }

            //onerror事件,标准浏览器支持，IE6,7,8,OPERA不支持，
            var supportScriptError = (!window.opera) && -[1,];
            if(supportScriptError){
                jsonpScript.onerror =function(){
                  //  utl.showMessage(utl.messageType.fail,"获取数据失败！",2000);
                    //删除全局变量的数据
                    delete window.fwJsonpData[""+curFwJsonpCallbackId];
                    jsonpScript.onreadystatechange = null;
                    document.getElementsByTagName("head")[0].removeChild(jsonpScript);
                }
            }

        }


        //在url末尾加上参数
        var query = "";
        for(var key in data){
            query +=  '&' + key + "=" + encodeURIComponent(data[key]);
        }
        var curFwJsonpCallbackId = ++window.fwJsonpCallbackId;
        query += "&callback="+"fwJsonpCallback"+(curFwJsonpCallbackId);
        query += "&t=" + new Date().getTime();
        url = url + ( url.indexOf('?') > -1 ? '&' : '?') + query.substring(1);
        //将服务器返回的JSON数据放到全局变量
        window["fwJsonpCallback"+curFwJsonpCallbackId] = function(data){
            window.fwJsonpData[""+curFwJsonpCallbackId] = data;
        };
        jsonpScript.src = url;
        document.getElementsByTagName("head")[0].appendChild(jsonpScript);
        //截取url的服务器地址部分并返回
        //function getServerUrl(aurl){
        //    var pos = 0;
        //    for(var i=0;i<4;i++){
        //        pos = aurl.indexOf("/", pos+1);
        //    }
        //    return aurl.substring(0,pos);
        //}
    }

    utl.drawCircleProcess = function($selector, diameter, processNumber) {

        $selector.each(function () {
            var process = processNumber.substring(0, processNumber.length - 1); //去掉百分号
            var radius =  diameter/2;

            var canvas = this;
            var context = canvas.getContext('2d');

            // 将绘图区域清空
            context.clearRect(0, 0, diameter, diameter);

            // 画全环形
            context.beginPath();
            context.moveTo(radius, radius);
            context.arc(radius, radius, radius, 0, Math.PI * 2, false);
            context.closePath();
            context.fillStyle = '#ffdfe0';   //浅色
            context.fill();


            // 画进度
            context.beginPath();
            context.moveTo(radius, radius);
            //true 逆时针  false 顺时针
            context.arc(radius, radius, radius,  0, Math.PI * 2 * process / 100, false);
            context.closePath();
            context.fillStyle = '#f14b46'; //深色
            context.fill();


            // 画内部空白
            context.beginPath();
            context.moveTo(radius, radius);
            context.arc(radius, radius, (radius-2), 0, Math.PI * 2, true);
            context.closePath();
            context.fillStyle = 'rgba(255,255,255,1)';
            context.fill();


            //在中间写字
            context.font = "14px Arial";
            context.fillStyle = '#f13a35';
            context.textAlign = 'center';
            context.textBaseline = 'middle';
            context.moveTo(radius, radius);


            //进行兼容性处理
            var IE_VERSION;
            try {
                IE_VERSION = +navigator.userAgent.match(/MSIE ([\d.]+)?/)[1];
            } catch (ex) {
                IE_VERSION = undefined;
            }
            if (IE_VERSION != null && (IE_VERSION == 7 || IE_VERSION == 8))
            {
                if (processNumber.toString().length == 2) {
                    context.fillText(processNumber, 20, 36);
                }
                else if (processNumber.toString().length == 4) {
                    context.fillText(processNumber, 13, 36);
                }
                else {
                    context.fillText(processNumber, 18, 36);
                }
            }
            else {
                context.fillText(processNumber, radius, radius);
            }


            

            return;
        });
    }

    jQuery.fn.showInputMoneyTip = function(){
        var pos = {
            top:$(this).offset().top,
            left:$(this).offset().left,
            width:$(this).outerWidth(),
            height:$(this).outerHeight()

        }
        var $tip = $("#input-money-tip");

        if($tip.length==0){
            alert("页面上需要有id为input-money-tip的元素");
            return;
        }
        var arrowHeight = $tip.find(".arrow-up").outerHeight();
        $tip.css({
            top:pos.top + pos.height + arrowHeight,
            left:pos.left
        }).show();

    }

    jQuery.fn.showInputStagingTip = function(){
        var pos = {
            top:$(this).offset().top,
            left:$(this).offset().left,
            width:$(this).outerWidth(),
            height:$(this).outerHeight()

        }
        var $tip = $("#input-staging-tip");

        if($tip.length==0){
            alert("页面上需要有id为input-staging-tip的元素");
            return;
        }
        var arrowHeight = $tip.find(".arrow-up").outerHeight();
        $tip.css({
            top:pos.top + pos.height + arrowHeight,
            left:pos.left
        }).show();

    }


    jQuery.fn.showInputOverTip = function(){
        var pos = {
            top:$(this).offset().top,
            left:$(this).offset().left,
            width:$(this).outerWidth(),
            height:$(this).outerHeight()

        }
        var $tip = $("#input-over-tip");

        if($tip.length==0){
            $('.m_main').append('<div class="input-tip" id="input-over-tip" style="display: none;">' +
                '<span class="arrow-up">' +
                '<span class="arrow-inner"></span>' +
                '</span><span class="warning">投资金额须小于等于标的剩余金额</span>' +
                '</div>');
            var $tip = $("#input-over-tip");
            /*alert("页面上需要有id为input-over-tip的元素");
            return;*/
        }
        var arrowHeight = $tip.find(".arrow-up").outerHeight();
        $tip.css({
            top:pos.top + pos.height + arrowHeight,
            left:pos.left
        }).show();

    }
    //新手标
    jQuery.fn.showInputNewTip = function(){
        var pos = {
            top:$(this).offset().top,
            left:$(this).offset().left,
            width:$(this).outerWidth(),
            height:$(this).outerHeight()

        }
        var $tip = $("#input-new-tip");

        if($tip.length==0){
            alert("页面上需要有id为input-new-tip的元素");
            return;
        }
        var arrowHeight = $tip.find(".arrow-up").outerHeight();
        $tip.css({
            top:pos.top + pos.height + arrowHeight,
            left:pos.left
        }).show();

    }
    //白金标
    jQuery.fn.showInputKingTip = function(){
        var pos = {
            top:$(this).offset().top,
            left:$(this).offset().left,
            width:$(this).outerWidth(),
            height:$(this).outerHeight()

        }
        var $tip = $("#input-king-tip");

        if($tip.length==0){
            alert("页面上需要有id为input-king-tip的元素");
            return;
        }
        var arrowHeight = $tip.find(".arrow-up").outerHeight();
        $tip.css({
            top:pos.top + pos.height + arrowHeight,
            left:pos.left
        }).show();

    }

    jQuery.fn.showInputTip = function(){
        var pos = {
            top:$(this).offset().top,
            left:$(this).offset().left,
            width:$(this).outerWidth(),
            height:$(this).outerHeight()

        }
        var $tip = $("#input-tip");

        if($tip.length==0){
            alert("页面上需要有id为input-tip的元素");
            return;
        }
        var arrowHeight = $tip.find(".arrow-up").outerHeight();
        $tip.css({
            top:pos.top + pos.height + arrowHeight,
            left:pos.left
        }).show();

    }

    jQuery.fn.showCustomInputTip = function(message){
        var pos = {
            top:$(this).offset().top,
            left:$(this).offset().left,
            width:$(this).outerWidth(),
            height:$(this).outerHeight()

        }

        var arr=[];
        arr.push('<div class="input-tip" id="input-money-tip" style="display: none;">  ');
        arr.push('      <span class="arrow-up">');
        arr.push('          <span class="arrow-inner"></span>');
        arr.push('      </span>');
        arr.push('      <span class="warning">{0}</span>'.format2(message));
        arr.push('</div>');


        var $tip = $(arr.join('\n'));
        $("body").append($tip);

        var arrowHeight = $tip.find(".arrow-up").outerHeight();
        $tip.css({
            top:pos.top + pos.height + arrowHeight,
            left:pos.left
        }).show();

    }

    utl.hideAllInputTip = function(){
        $(".input-tip").hide();
    }

    utl.showSimplePopBox = function(options){
        var that = {};
        that.DEFAULT = {
            showBackdrop:true,
            title:'',
            messageType:utl.messageType.warning,
            message:'&nbsp;',
            btnText:'确定',
            btnClickFunction:function(){
                utl.hideSimplePopBox(this);
            }, //blank function
            isSimpleMessage:true,
            contentTemplate:'',
            popboxClass:'',
            popboxStyle:''
        }
        $.extend(that, that.DEFAULT, options);

        var $html = $("html");
        var $body = $(document.body);
        var $m_main = $('.m_main');


        $html.css({
            "overflowY":"hidden",
            "overflowX":"hidden"
        });


        //backdrop加在$m_main上面，而popbox加在body上面,要注意！
        if(that.showBackdrop){
            utl.showBackdrop($m_main);
        }
        var arr = [];
        arr.push('   	<div class="popbox {0}" style="{1}">	'.format2(that.popboxClass, that.popboxStyle));
        arr.push('      	<div class="ie7cell ie7cell-in-popbox">     ');
        arr.push('      		<div class="popbox-header">     ');
        arr.push('      			<div class="ie7cell ie7cell-in-popbox-header">     ');
        arr.push('      			<span class="popbox-title">{0}</span>     '.format2(that.title || ''));
        arr.push('      				<span class="close"></span>     ');
        arr.push('      			</div>     ');
        arr.push('      		</div>     ');
        arr.push('      		<div class="popbox-body">     ');
        arr.push('      			<div class="ie7cell ie7cell-in-popbox-body">     ');
        //arr.push('      				<div class="eachline">     ');
        //arr.push('      					<span class="message">{0}</span>     '.format2(that.message));
        //arr.push('      				</div>     ');
        //arr.push('      				<div class="eachline">     ');
        //arr.push('      					<a href="javascript:void(0);" class="btn-ok" >{0}</a>     '.format2(that.btnText));
        //arr.push('      				</div>     ');
        arr.push('      			</div>     ');
        arr.push('   			</div>     ');
        arr.push('   		</div>     ');
        arr.push(' 				</div>  ')


        var $popbox = $(arr.join('\n'));
        $m_main.find(".popbox").remove();

        $m_main.append($popbox);

        if(that.isSimpleMessage===true){
            //0.
            arr = [];
            arr.push('      				<div class="eachline">     ');
            arr.push('      					<span class="message">{0}</span>     '.format2(that.message));
            arr.push('      				</div>     ');
            arr.push('      				<div class="eachline">     ');
            arr.push('      					<a href="javascript:void(0);" class="btn-ok" >{0}</a>     '.format2(that.btnText));
            arr.push('      				</div>     ');

            $popbox.find(".popbox-body .ie7cell-in-popbox-body").html(arr.join('\n'));

            //1.解决单行还是双行显示问题
            var $messageSpan = $popbox.find(".message");
            if($messageSpan.outerHeight()<35){
                $messageSpan.css("lineHeight","50px");
            }else{
                $messageSpan.css("lineHeight","28px");
            }
            //2.解决messsageType问题
            switch (that.messageType){
                case utl.messageType.success:
                    $messageSpan.addClass("success");
                    break;
                case utl.messageType.warning:
                    $messageSpan.addClass("warning");
                    break;
                case utl.messageType.fail:
                    $messageSpan.addClass("fail");
                    break;
                default:
                    $messageSpan.addClass("warning");
                    break;
            }
            if('[object Function]' === Object.prototype.toString.call(that.btnClickFunction)){
                $popbox.off("click.popbox",".btn-ok").on("click.popbox",".btn-ok",function(event){
                    var $sender = $(event.currentTarget);
                    event.stopPropagation();
                    event.preventDefault();
                    $.proxy(that.btnClickFunction,$popbox)();
                });
            }
        }
        else{
            $popbox.find(".popbox-body .ie7cell-in-popbox-body").empty().append(that.contentTemplate);
        }

        //0。解决居中问题
        var repostionPopbox = function(){
            if($popbox.is(":visible")){
                var pos = {
                    width:$popbox.outerWidth(),
                    height:$popbox.outerHeight()
                }

                $popbox.css({
                    left:'50%',
                    top:'50%',
                    marginLeft:( -(+pos.width) / 2),
                    marginTop:( -(+pos.height) / 2)
                });




                //定位原理-> 先定位到m_main的中间50%的位置 --》当前window的中间 --》再考虑滚动条的滚动距离
                $html.css({
                    "overflowY":"auto",
                    "overflowX":"auto"
                });
                var SCROLLTOP =$(window).scrollTop();
                if($popbox.is(":visible") && that.showBackdrop){
                    $html.css({
                        "overflowY":"hidden",
                        "overflowX":"hidden"
                    });
                }

                var marginTop = parseFloat($popbox.css("marginTop"));
                $popbox.css("marginTop",marginTop - ($(".m_main").outerHeight() - $(window).outerHeight())/2 +SCROLLTOP )

            }

        }
        $popbox.show();
        repostionPopbox();

        $popbox.off("click.popbox",".close").on("click.popbox",".close",function(event){
            var $sender = $(event.currentTarget);
            event.stopPropagation();
            event.preventDefault();
            utl.hideSimplePopBox($popbox);
        });

        $(window).off("resize.popbox").on("resize.popbox",function(){
            if(that.showBackdrop){
                var $mask = $m_main.data("mask");
                $mask.css({
                    width:$m_main.outerWidth(),
                    height:$m_main.outerHeight()
                })
            }
            repostionPopbox();
        });








    }

    utl.hideSimplePopBox = function($popbox){
        if($popbox && $popbox instanceof jQuery &&  $popbox.length>0){
            $popbox.hide();
        }
        else{
            $("body > .popbox").hide();  //hide all popbox
        }
        utl.hideBackdrop();
        var $html = $("html");
        $html.css({
            "overflowY":"auto",
            "overflowX":"auto"
        });
    }

    utl.showBackdrop = function ($parentEl, opacity) {
        if ($parentEl == undefined) {
            $parentEl = $('body>.m_main');
        }

        if (opacity == undefined) {
            opacity = 50;
        }

        $parentEl.each(function (index, elem) {
            var $each_parentEl = $(elem);

            $each_parentEl.css({
                "position": "relative"
            });

            var $mask = $("<div></div>").addClass("mask");
            switch (opacity) {
                case 12:
                    $mask.addClass("mask12");
                    break;
                case 50:
                    $mask.addClass("mask50");
                    break;
                case 60:
                    $mask.addClass("mask60");
                    break;
                case 70:
                    $mask.addClass("mask70");
                    break;
                case 80:
                    $mask.addClass("mask80");
                    break;
                case 90:
                    $mask.addClass("mask90");
                    break;
                default:
                    $mask.addClass("mask12");   //默认12%透明度
                    break;
            }

            var height = $each_parentEl.outerHeight();
            var width = $each_parentEl.outerWidth();
            $mask.css(
                {
                    "height": height,
                    "width": width,
                    "position": "absolute"
                }
                ).appendTo($each_parentEl);

            $each_parentEl.data("mask", $mask);

            
        });

        return $parentEl;

    }

    utl.hideBackdrop = function ($parentEl, isRemove) {
        if ($parentEl == undefined) {
            $parentEl = $('body > .m_main');
        }

        $parentEl.each(function (index, elem) {
            var $each_parentEl = $(elem);

            if ($each_parentEl.data("mask")) {
                var $mask = $each_parentEl.data("mask").hide();

                if (isRemove) {
                    $mask.remove()
                    $each_parentEl.removeData("mask");
                }
            }
        });

        return $parentEl;
 
    }

    utl.getImgcodeSection = function(){
        arr= [];
        arr.push('   	<div class="imgcode-section">																	');
        arr.push('      	<div class="label-wrapper">                                                                 ');
        arr.push('      		<div class="ie7cell">                                                          ');
        arr.push('      			<span class="label">验证码：</span>                                                 ');
        arr.push('      		</div>                                                                                  ');
        arr.push('      	</div>                                                                                      ');
        arr.push('      	<div class="input-wrapper">                                                           ');
        arr.push('      		<div class="ie7cell">                                                  ');
        arr.push('      			<input type="text" class="input"  id="imageCode" />                                                 ');
        arr.push('      		</div>                                                                                  ');
        arr.push('      	</div>                                                                                      ');
        arr.push('      	<div class="img-wrapper">                                                                   ');
        arr.push('      		<div class="ie7cell">                                            ');
        arr.push('      			<img class="img" />               ');
        arr.push('      		</div>                                                                                  ');
        arr.push('      	</div>                                                                                      ');
        arr.push('      	<div class="refresh-wrapper">                                                               ');
        arr.push('      		<div class="ie7cell">                                        ');
        arr.push('      			<a class="refresh" href="javascript:void(0);"  onclick="initVertifyCode()"></a>                                  ');
        arr.push('   			</div>                                                                                  ');
        arr.push('   		</div>                                                                                      ');
        arr.push(' 			<div class="valid-wrapper">                                                                 ');
        arr.push('						<div class="ie7cell">                                  ');
        arr.push('               			<div class="valid">&nbsp;</div>                                         ');
        arr.push('                		</div>                                                                          ');
        arr.push('               	</div>                                                                              ');
        arr.push('               </div>                                                                                 ');

        return arr.join("\n");
    }

    utl.getOkSection = function(){
        arr = [];
        arr.push(' <div class="ok-section">                  ');
        arr.push(' <a href="javascript:void(0);" class="btn-ok" onclick="onSubWithImg()" >确定</a> ');
        arr.push(' </div>                                           ');

        return arr.join("\n");
    }

    function onSubWithImg(){
        btnTypeFlag=2;//2代表是点击图形验证码的确定，1代表点击的是登录
        $('#btnlogin').click();
    }


    utl.showLittleComputer = function(){
        var arr=[];
        arr.push('   <div class="benifit-computer">');
        arr.push('      <iframe width="100%" height="100%" style="width: 100%;height: 100%;" frameborder="0" scrolling="no" src="littleComputer.html"></iframe>      ');
        arr.push('  </div> ');

        var $div = $(arr.join('\n')).appendTo($(".m_main"));


        utl.centerWindow($div);
        $("html").css({
            "overflow-x":"hidden",
            "overflow-y":"hidden"
        });
        utl.showBackdrop();

    }
    utl.showLittleLoginPopbox = function () {
        var arr=[];
        arr.push('   <div class="little-login-popbox">');
        arr.push('      <iframe width="326" height="376"frameborder="0" scrolling="no" src="littleLoginPopbox.html"></iframe>      ');
        arr.push('  </div> ');

        var $div = $(arr.join('\n')).appendTo($(".m_main"));

        utl.centerWindow($div);
        $("html").css({
            "overflow-x":"hidden",
            "overflow-y":"hidden"
        });
        utl.showBackdrop(undefined, 60);

    }

    utl.showImgcodePopbox = function(){
        var $contentTemplate = $(utl.getImgcodeSection()+utl.getOkSection());
        initVertifyCode();
        utl.showSimplePopBox({
        	title:'请输入验证码',
        	isSimpleMessage:false,
        	contentTemplate:$contentTemplate,   //此参数支持jQuery对象
        	popboxStyle:'width:450px;',
        	popboxClass:'XXX'
        });
    }

    //初始化图形验证码
    function initVertifyCode() {
        var imageUrl = window.config.SERVER_URL + "/user/genernateImage.do?imgNum=" + getRandom(100);
        utl.jsonpRequest(imageUrl, "", function (result) {
            if (result.code == "0000") {
                $('.img').attr('src', window.config.SERVER_URL + result.result.img);
                tokenverifyCode = result.result.tokenverifyCode;
            } else {
                $('.img').attr('src', "");
                tokenverifyCode = "";
            }
        });
    }

    function getRandom(n) {
        return Math.floor(Math.random() * n + 1)
    }

    utl.showLittleSmsCodePopbox = function(){

        var arr=[];
        arr.push('   <div class="little-smscode-popbox">');
        arr.push('      <iframe width="100%" height="100%" id="sms" style="width: 100%;height: 100%;" frameborder="0" scrolling="no" src="littleSmsCodePopbox.html"></iframe>      ');
        arr.push('  </div> ');

        var $div = $(arr.join('\n')).appendTo($(".m_main"));

        utl.centerWindow($div);
        $("html").css({
            "overflow-x":"hidden",
            "overflow-y":"hidden"
        });
        utl.showBackdrop();

    }

    //必须是.m_main的DIV下面的元素
    utl.centerWindow = function($div){
        //此函数只适用于$m_main元素的子元素中的
        if($(".m_main").find($div).length==0){
            alert("元素必须是$('.m_main')的子元素");
            return;
        }
        var repostionDiv = function(){
            if($div.is(":visible")){
                var $html = $("html");
                var pos = {
                    width:$div.outerWidth(),
                    height:$div.outerHeight()
                }

                $div.css({
                    left:'50%',
                    top:'50%',
                    marginLeft:( -(+pos.width) / 2),
                    marginTop:( -(+pos.height) / 2)
                });

                //定位原理-> 先定位到m_main的中间50%的位置 --》当前window的中间 --》再考虑滚动条的滚动距离
                var SCROLLTOP =$(window).scrollTop();
                //var top  =  parseFloat($div.css("top")) || 0;
                //$div.css("top",top - ($(".m_main").outerHeight() - $(window).outerHeight())/2 +SCROLLTOP );

                var marginTop = parseFloat($div.css("marginTop"));
                $div.css("marginTop",marginTop - ($(".m_main").outerHeight() - $(window).outerHeight())/2 +SCROLLTOP )
            }

        }
        repostionDiv();
    }

    //用 margin-left,margin-top，left=50%, top=50%实现居中
    utl.center = function ($div, $parentEl, duration) {
    if ($parentEl == undefined) {
        $parentEl = $("body");
    }

    if ($parentEl.css("position") == "static") {
        $parentEl.css("position", "relative");
    }

    var resize = function () {
        var css = {
            "left":"50%" ,
            "top": "50%"
        }

        var thisWidth = $div.outerWidth(false),
            thisHeight = $div.outerHeight(false);

        css["marginLeft"] = -thisWidth / 2;
        css["marginTop"] = -thisHeight / 2;

        $div.css({ 'position': 'absolute' });

        if(duration==undefined){
            $div.css(css);
        }
        else{
            $div.animate(css, duration);
        }

    }

    resize($div);
}

    //用纯absolute,相对于 - 父元素 -  实现居中定位
    utl.center2 = function ($elem, $parentEl, options, duration) {

    if ($parentEl == undefined) {
        $parentEl = $("body");
    }

    if ($parentEl.css("position") == "static") {
        $parentEl.css("position", "relative");
    }

    var resize = function ($elem, options, duration) {
        var css = {};
        if (options.horizontal != false) {
            var parentWidth = $parentEl.width(),
                thisWidth = $elem.width(),
                newLeft = (parentWidth - thisWidth) / 2;
            css['left'] = newLeft;
        }
        if (options.vertical != false) {
            var parentHeight = $parentEl.height(),
                thisHeight = $elem.height(),
                newTop = (parentHeight - thisHeight) / 2;
            css['top'] = newTop;
        }
        $elem.css({ 'position': 'absolute' });
        if(duration==undefined){
            $elem.css(css);
        }
        else{
            $elem.animate(css, duration);
        }
    }

    var leThis = $elem;
    var settings = $.extend({}, { 'horizontal': true, 'vertical': true }, options ? options : {});
    resize($elem, settings);

}

    utl.capitalize = function (string) {
        return string.charAt(0).toUpperCase() + string.slice(1);
    }

    //打乱数组元素
    utl.shuffle = function (arr) {
        for (var j, x, i = arr.length; i; j = parseInt(Math.random() * i, 10), x = arr[--i], arr[i] = arr[j], arr[j] = x);
        return arr;
    };


    utl.showLoadingSpin = function ($elem, option, color) {
        if(jQuery.fn.spin==undefined){
            alert("请先引入jquery、spin.js和jquery.spin.js插件");
            return;
        }


        if ($elem == undefined) {
            $elem = $('.m_main');
        }

        //默认#浅灰色
        if(color==undefined){
            color="#999";
        }

        if ($elem.css("position") == "static") {
            $elem.data("pre_position", "static");
            $elem.css("position", "relative");
        }
    


        var $spin = $elem.spin(option, color);
        $elem.data("spin", $spin);

    }

    utl.hideLoadingSpin = function ($elem, isRemove) {
        if (jQuery.fn.spin == undefined) {
            alert("请先引入jquery、spin.js和jquery.spin.js插件");
            return;
        }

        if ($elem == undefined) {
            $elem = $('.m_main');
        }

        if ($elem.data("pre_position") != null) {
            $elem.css("position", "relative");
            $elem.removeData("pre_position");
        }

        var $spin = $elem.data("spin");
        if ($spin != null) {
            $spin.spin(false);

            if (isRemove) {
                $spin.remove();
            }
        }
    }

    //显示loading图标函数
    utl.showLoadingMessage = function (isMask, message, $parentEl) {
        if ($parentEl == undefined) {
            $parentEl = $('.m_main');
        }
        if (isMask) {
            utl.showBackdrop($parentEl);
        }
        if ($(".hx_loading_box").length != 0)
            $(".hx_loading_box").remove();
        var mask_message = $("<div class='hx_loading_box'></div>");
        var loading_img = $('<div class="hx_loading_box-spinner"><div class="hx_loading_box-spinner-container container1"><div class="circle1"></div><div class="circle2"></div><div class="circle3"></div><div class="circle4"></div></div><div class="hx_loading_box-spinner-container container2"><div class="circle1"></div><div class="circle2"></div><div class="circle3"></div><div class="circle4"></div></div><div class="hx_loading_box-spinner-container container3"><div class="circle1"></div><div class="circle2"></div><div class="circle3"></div><div class="circle4"></div></div></div>');
        if (navigator.userAgent.indexOf("MSIE 9.0") == -1 && navigator.userAgent.indexOf("MSIE 8.0") == -1 && navigator.userAgent.indexOf("MSIE 7.0") == -1) {
            mask_message.append(loading_img);
        }
        else
            mask_message.addClass("hx_loading_box_fix_ie");

        mask_message.appendTo($parentEl);

        if (message == '' || message == undefined)
            message = utl.loadingMessage;
        mask_message.append(message);
        if (mask_message.width() > 500) {
            mask_message.css({ "font-size": "12px" }).width(500);
        }

        mask_message.css("marginLeft", -mask_message.outerWidth() / 2);
        mask_message.css("marginTop", -mask_message.outerHeight() / 2);
        mask_message.addClass("bg_black");
    }

    utl.hideLoadingMessage = function ($parentEl) {

        if ($parentEl == undefined) {
            $parentEl = $('.m_main');
        }
        if ($parentEl.find(".hx-mask").length != 0)
            $parentEl.find(".hx-mask").remove();
        if ($parentEl.find(".hx_loading_box").length != 0)
            $parentEl.find(".hx_loading_box").remove();
    }

    // begin ajax helper        --》》》

    utl.showRequestLoading = function (loadingMessage, $el) {
        if (loadingMessage != null)
        {
            utl.showLoadingMessage(false, loadingMessage);
        } else if ($el != null) {
            utl.showLoadingMessage(false, loadingMessage, $el);
        }
    }
    utl.hideRequestLoading =  function () {
        utl.hideLoadingMessage();
    }

    utl.showRequestErrorMessage =  function (errorMessage) {
        utl.showMessage(utl.messageType.fail, errorMessage, '', true);
    }
    utl.responseSuccessProcess =  function (res) {
        utl.hideRequestLoading();
        if (res.error) {
            utl.showRequestErrorMessage(res.message);
            return $.Deferred().reject(res);
        }
        return res;
    }
    utl.reponseFailProcess = function (aj) {
        var errorMessage = '获取数据出错';
        utl.hideRequestLoading();
        if (aj.statusText != 'abort') {
            utl.showRequestErrorMessage(errorMessage);
        }
        return $.Deferred().reject({ error: true, message: errorMessage });
    }

    utl.makeRequest =  function (url, param, loadingMessage) {
        utl.showRequestLoading(loadingMessage);
        var jq = $.ajax({
            type: "POST",
            url: url + (url.indexOf('?') > -1 ? '&' : '?') + 'bust=' + new Date().getTime(),
            data: param,
            dataType: 'json'
        });
        return jq.then(utl.responseSuccessProcess, utl.reponseFailProcess);
    }
    utl.makeRequestLeft = function (url, param, loadingMessage, $el) {

        utl.showRequestLoading(loadingMessage, $el);
        var jq = $.ajax({
            type: "POST",
            url: url + (url.indexOf('?') > -1 ? '&' : '?') + 'bust=' + new Date().getTime(),
            data: param,
            dataType: 'json'
        });
        return jq.then(utl.responseSuccessProcess, utl.reponseFailProcess);
    }

    //spin形式的没有遮罩层
    utl.showRequestLoadingSpin = function ($el) {
        utl.LOADINGSPINER= utl.showLoadingSpin($el);
    }
    utl.hideRequestLoadingSpin = function () {
        utl.hideLoadingSpin(utl.LOADINGSPINER);
    }

    utl.responseSuccessProcessSpin = function (res) {
        utl.hideRequestLoadingSpin();
        if (res.error) {
            utl.showRequestErrorMessage(res.message);
            return $.Deferred().reject(res);
        }
        return res;
    }
    utl.reponseFailProcessSpin = function (aj) {
        var errorMessage = '获取数据出错';
        utl.hideRequestLoadingSpin();
        if (aj.statusText != 'abort') {
            utl.showRequestErrorMessage(errorMessage);
        }
        return $.Deferred().reject({ error: true, message: errorMessage });
    }
    

    utl.makeRequestSpin = function (url, param) {
        utl.showRequestLoadingSpin();
        var jq = $.ajax({
            type: "POST",
            url: url + (url.indexOf('?') > -1 ? '&' : '?') + 'bust=' + new Date().getTime(),
            data: param,
            dataType: 'json'
        });
        return jq.then(utl.responseSuccessProcessSpin, utl.reponseFailProcessSpin);
    }
    utl.makeRequestSpinLeft = function (url, param, $el) {

        utl.showRequestLoadingSpin($el);
        var jq = $.ajax({
            type: "POST",
            url: url + (url.indexOf('?') > -1 ? '&' : '?') + 'bust=' + new Date().getTime(),
            data: param,
            dataType: 'json'
        });
        return jq.then(utl.responseSuccessProcessSpin, utl.reponseFailProcessSpin);
    }



    //打开新window的防阻止方式
    utl.openwin = function (url) {
        var a = document.createElement("a");
        a.setAttribute("href", url);
        a.setAttribute("target", "_blank");
        a.setAttribute("id", "camnpr");
        document.body.appendChild(a);
        a.click();
    }

    utl.setExpireDate = function($expireDateDiv, expireDate){

        //expireDate注意是 getTime格式
        var timer1;
        var show_date_time_0 = function () {

            var today = new Date();
            //计算目标时间与当前时间间隔(毫秒数)
            var timeold = expireDate - today.getTime(); //getTime 方法返回一个整数值，这个整数代表了从 1970 年 1 月 1 日开始计算到 Date 对象中的时间之间的毫秒数。

            //计算目标时间与当前时间的秒数
            var sectimeold = timeold / 1000;

            //计算目标时间与当前时间的秒数(整数)
            var secondsold = Math.floor(sectimeold);

            //计算一天的秒数
            var msPerDay = 24 * 60 * 60 * 1000;

            //得到剩余天数
            var e_daysold = timeold / msPerDay;
            //得到剩余天数(整数)
            var daysold = Math.floor(e_daysold);

            //得到剩余天数以外的小时数
            var e_hrsold = (e_daysold - daysold) * 24;
            //得到剩余天数以外的小时数(整数)
            var hrsold = Math.floor(e_hrsold);

            //得到尾剩余分数
            var e_minsold = (e_hrsold - hrsold) * 60;
            //得到尾剩余分数(整数)
            var minsold = Math.floor((e_hrsold - hrsold) * 60);

            //得到尾剩余秒数(整数)
            var seconds = Math.floor((e_minsold - minsold) * 60);
            //判断过期
            if (secondsold < 0) {
                $expireDateDiv.html("已结束");
                clearInterval(timer1);
            }
            else {
                //小时取两位,不足补0
                if (hrsold < 10) {
                    hrsold = "0" + hrsold;
                }
                //分数取两位,不足补0
                if (minsold < 10) {
                    minsold = "0" + minsold;
                }
                //秒数取两位,不足补0
                if (seconds < 10) {
                    seconds = "0" + seconds;
                }
                $expireDateDiv.html(daysold + "天" + hrsold + "小时" + minsold + "分" + seconds + "秒");
            }


        }

        timer1 = setInterval(show_date_time_0, 1000);
    }

    utl.reformateDate  = function(expireDate){
        expireDate = expireDate.toString().replace(/-/g,"&^").replace(/ /g,"&^").replace(/:/g,"&^");

        var arr = expireDate.split("&^");
        arr[1] = parseInt(arr[1]) - 1;

        expireDate = new Date(
           arr[0]|| 0,
           arr[1]|| 0,
           arr[2]|| 0,
           arr[3]|| 0,
           arr[4]|| 0,
           arr[5]|| 0
        )
        return expireDate;
    }

    utl.goToRealName_lzj = function () {
        var $popbox = $('    <style>        .maskStyle {background-color: #787878;color: #787878;z-index: 9999998;filter: alpha(opacity=50);opacity: 0.8;        position: fixed;top: 0%;left: 0%;    }    </style><div id="maskall"></div>    <div id="showpop" class="popbox " style="z-index: 9999999;display:none;left: 50%;width: 700px;    border: none;">        <div class="ie7cell ie7cell-in-popbox">            <div class="popbox-header">                <div class="ie7cell ie7cell-in-popbox-header">                    <span class="popbox-title">重要提示</span>                    <span id="popboxclose" class="close"></span>                </div>            </div>            <div class="popbox-body">                <div class="ie7cell ie7cell-in-popbox-body">                    <div class="eachline">                        <span class="message warning" style="line-height: 50px;">您还未进行实名认证，为了您的账户安全，请实名后进行投资！</span>        </div>                    <div class="eachline">   <a id="gotoRealname" href="http://www.hanxinbank.com/bankcard.aspx?returnurl=http%3A%2F%2Fwww.hanxinbank.com%2FproductList.html" class="btn-ok">立即认证</a>                    </div>                </div>            </div>        </div>    </div>');
    
        var $m_main = $('.m_main');
        $m_main.find("#showpop").remove();
        $m_main.append($popbox);
        setMask();
    }


    //关闭按钮
    $(document).off("click", "#popboxclose").on("click", "#popboxclose", function (event) {
        $('#maskall').hide();
        $('#showpop').hide();
    })
    function setMask(ev) {
        //蒙版层
        var CoverAll = getViewportInfo();
        var W = CoverAll.w;
        var H = CoverAll.h;
        $('#maskall').css("opacity", "0.3").css("width", '' + W + 'px').css("height", '' + H + 'px');
        $('#maskall').addClass('maskStyle');
        $('#maskall').show();
        $('#showpop').css('margin-left', '-300px').css("top", H / 2 - 127 + CoverAll.t + 'px');
        $('#showpop').show();
    }


    //蒙版的尺寸
    function getViewportInfo() {
        var w = (window.innerWidth) ? window.innerWidth : (document.documentElement && document.documentElement.clientWidth) ? document.documentElement.clientWidth : document.body.offsetWidth;

        var h = (window.innerHeight) ? window.innerHeight : (document.documentElement && document.documentElement.clientHeight) ? document.documentElement.clientHeight : document.body.offsetHeight;

        var t = document.documentElement.scrollTop > document.body.scrollTop ? document.documentElement.scrollTop : document.body.scrollTop;
        return { w: w, h: h ,t:t};
    };


