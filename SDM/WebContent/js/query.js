	var min, max, avg, sum, s = '';

	$(window)
			.on(
					"load",
					function() {
						$('#stream-info').html(
								"<b>Stream_ID : "
										+ localStorage.getItem("stream_id")
										+ "</b>");
						var s = '';
						var attr = localStorage.getItem("attr_string");
						var a = attr.split(":");
						for (i = 0; i < a.length; i++) {
							s = s
									+ '<div class="col-md-5"><div class="panel panel-default">'
									+ '<div class="panel-heading" style="background-color: #ffffff;">'
									+ '<span> <b>Output of query: &nbsp&nbsp'
									+ a[i]
									+ '</b></span></div><div class="panel-body"  style="overflow-y:scroll;height:300px" id="output'+a[i]+'"></div><div class="panel-footer"></div></div></div>';
						}
						$('#output-panels').html(s);

					});

	  	setInterval(function(){getResult();}, 1000);
	 function getResult() {
		var t = $('#select-operation').val();
		console.log();
		if (t == "min")
			min = 1;
		else
			min = 0;
		if (t == "max")
			max = 1;
		else
			max = 0;
		if (t == "avg")
			avg = 1;
		else
			avg = 0;
		if (t == "sum")
			sum = 1;
		else
			sum = 0;
		if (t == "all") {
			min = 1;
			max = 1;
			avg = 1;
			sum = 1;
		}
		if (t == "mm") {
			min = 1;
			max = 1;
			avg = 0;
			sum = 0;
		}

		if (t == "sa") {
			min = 0;
			max = 0;
			avg = 1;
			sum = 1;
		}
		console.log(formToJSON());
		rootURL = "/SDM/webapi/SDM/computeResult";
		console.log(rootURL);

		var idd = localStorage.getItem("stream_id");
		var attr_string = localStorage.getItem("attr_string");
		rootURL = "/SDM/webapi/SDM/getResult/" + idd + "/" + max + "/" + min
				+ "/" + avg + "/" + sum + "/" + attr_string;
		console.log(rootURL);
		$.ajax({
			type : 'GET',
			url : rootURL,
			async : true,
			beforeSend : function(xhr) {
				if (xhr && xhr.overrideMimeType) {
					xhr.overrideMimeType('application/json;charset=utf-8');
				}
			},
			dataType : 'json',
			success : function(obj) {
				console.log(obj);
				for (j = 0; j < obj.length; j++) {
					var i = 0;
					var s = '';
					var s1;
					if (obj.length == 0)
						s1 = '<div><h4>Attribute :&nbspNot Chosen</h4></div>';
					else
						s1 = '';
					while (i < obj[j].rlist.length) {

						if (min == 1)
							s = s + '<b>min</b> : ' + obj[j].rlist[i].min
									+ '&nbsp&nbsp';
						if (max == 1)
							s = s + '<b>max</b> : ' + obj[j].rlist[i].max
									+ '&nbsp&nbsp';
						if (avg == 1)
							s = s + '<b>avg</b> : ' + obj[j].rlist[i].avg
									+ '&nbsp&nbsp';
						if (sum == 1)
							s = s + '<b>sum</b> : ' + obj[j].rlist[i].sum
									+ '&nbsp&nbsp';
						i++;
						s = s + '<br>';
					}
					var str = s1 + '<div>' + s + '</div><br>';
					$('#output' + obj[j].attribute).html(str);
				}
			},
			error : function(textStatus, errorThrown) {
				console.log(textStatus.overrideMimeType);
			}
		});
		localStorage.setItem("load", 1);
	}

	function formToJSON() {
		return JSON.stringify({
			"stream_id" : localStorage.getItem("stream_id"),
			"min" : min,
			"max" : max,
			"avg" : avg,
			"sum" : sum,
			"attribute" : $('#select-attribute').val()
		});
	}

	$('#go-back').on("click", function() {

		window.location = "streams.html";
	});
