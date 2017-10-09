!function($, layer) {

	/** ************************* json相关 ************************* */

	/**
	 * 将js对象序列化成json文本
	 */
	function stringify(obj) {
		return JSON.stringify(obj);
	}

	/**
	 * 将json文本转换为js对象
	 */
	function parse(jsonText) {
		return $.parseJSON(jsonText);
	}

	/** ************************* 弹出层 ************************* */

	/**
	 * toast
	 * 
	 * @param msg
	 *            提示消息
	 * @param duration
	 *            持续时长
	 */
	function toast(msg) {
		var index = layer.msg(msg);
		return index;
	}

	/**
	 * 只有一个确认按钮的确认框
	 * 
	 * @param title
	 *            标题
	 * @param content
	 *            确认文本
	 * @param callback
	 *            点击确认触发的函数
	 */
	function alert(title, content, callback) {
		var index = layer.alert(content, {
			title : title,
			skin : 'layui-layer-lan',
			closeBtn : 0,
			anim : 1
		}, callback);
		return index;
	}

	/**
	 * 一个确认按钮、一个取消按钮的询问框
	 * 
	 * @param title
	 *            标题
	 * @param content
	 *            确认文本
	 * @param okText
	 *            确认按钮的文本
	 * @param okCallback
	 *            点击确认的回调函数
	 * @param cancelText
	 *            取消按钮的文本
	 * @param cancelCallback
	 *            点击取消的回调函数
	 */
	function confirm(title, content, okText, okCallback, cancelText,
			cancelCallback) {
		layer.confirm(content, {
			title : title,
			btn : [ okText, cancelText ]
		}, function() {
			okCallback();
		}, function() {
			cancelCallback();
		});
	}

	/** ************************* form表单相关 ************************* */

	/**
	 * 构建表单提交
	 */
	function postfrom(url, data) {
		var $body = $(document.body);

		var $form = $("<form method='post'></form>");
		$form.attr("action", url);

		if (data) {
			$.each(data, function(key, value) {
				var $input = $("<input name=" + key + " type='hidden'>");
				$input.val(value);
				$form.append($input);
			});
		}

		$form.appendTo($body);
		$form.submit();
		$body.remove($form);
	}

	/**
	 * 发送ajax post异步请求
	 */
	function postAjax(url, data, callback, dataType) {
		$.ajax({
			url : url,
			type : "post",
			data : data,
			dataType : dataType || "json",
			success : callback,
			error : function() {
				toast("抱歉，通讯异常！");
			}
		})
	}

	/**
	 * 验证ajax结果，有错误直接弹窗处理结果，没有错误返回true
	 */
	function handleAjaxResult(result, successCallback) {
		var type = result.type || "failed";
		if (type == "error") {
			var msg = result.msg || "抱歉，系统异常！";
			toast(msg);
		} else if (type == "failed") {
			var msg = result.msg || "抱歉，操作失败！";
			toast(msg);
		} else if (type == "false") {
			var msg = result.msg || "抱歉，验证失败！";
			toast(msg);
		} else if (type == "success") {
			successCallback(result);
		}
	}

	/** ************************* handlebar template相关 ************************* */

	/**
	 * handlebar渲染模板
	 */
	function renderTemplate($wrapper, $tpl, data) {
		var source = $tpl.text();
		var template = Handlebars.compile(source);
		var html = template(data);
		$wrapper.html(html);
	}

	/** ************************* 延迟加载js文件 ************************* */

	/**
	 * 延迟加载js文件
	 */
	function loadjs($dom, jsurl) {
		var $script = '<script src="' + jsurl + '"></script>';
		$dom.append($script);
	}

	/** ************************* mc||mcutils ************************* */

	var mcutils = {// json相关
		json : {
			stringify : stringify,
			parse : parse,
		},
		// 弹窗相关
		toast : toast,
		alert : alert,
		confirm : confirm,
		// form表单相关
		postform : postfrom,
		postAjax : postAjax,
		handleAjaxResult : handleAjaxResult,
		// handlebar template
		renderTemplate : renderTemplate,
		render : renderTemplate,
		// 延迟加载js文件
		loadjs : loadjs,
	};

	/**
	 * <br>
	 * mcutils.json.stringify();<br>
	 * mcutils.json.parse();<br>
	 * <br>
	 * mcutils.toast();<br>
	 * mcutils.alert(); <br>
	 * mcutils.confirm();<br>
	 * <br>
	 * mcutils.postfrom();<br>
	 * mcutils.postAjax();<br>
	 * mcutils.validateAjax();<br>
	 * <br>
	 * mcutils.renderTemplate();<br>
	 * mcutils.loadjs();<br>
	 * <br>
	 */
	window.mc = window.mcutils = mcutils;

}($, layer);