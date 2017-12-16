var streams;
var stream_name;
$(window).on("load",function(){
		rootURL = "/SDM/webapi/SDM/getStreamInfo";
		$.ajax({
			 type: 'GET',
			 url: rootURL,
			 async: true,
			 beforeSend: function (xhr) {
			   if (xhr && xhr.overrideMimeType) {
			     xhr.overrideMimeType('application/json;charset=utf-8');
			   }
			 },
			 dataType: 'json',
			 success: function (obj) {
				// console.log(obj);
				 streams = obj;
				 
			 	 	var str ='<option  value="-1" >Select</option>';
				for (var i=0;i<obj.length;i++){
					str = str + '<option value ="'+obj[i].id+'" >'+obj[i].name+'</option>';
				}
			//	console.log(str);
				$('#select-stream').html(str);  
				
			 }
		});

		
		
});

function submitform(){
    localStorage.setItem("stream_id",$('#select-stream').val());
    localStorage.setItem("stream_name",$('#stream-name').html());
 		var checkedValues = $('.form-check-input:checked').map(function() {
		    return this.value;
		}).get();
		console.log(checkedValues);
		var input = checkedValues[0];
		for(i=1;i<checkedValues.length;i++){
			input = input +":"+checkedValues[i]; 
		}
		localStorage.setItem("attr_string",input);
		window.location="query.html";
} 
	$('#select-stream')
			.change(
					function() {
				//		alert($('#select-stream').val());
						if ($('#select-stream').val() == -1)
							document.getElementById("list-attrbutes").innerHTML = "";
						else {
						//	console.log(streams);
							var i = 0;
							while (streams[i].id != $('#select-stream').val())
								i++;
							$('#stream-name').html(streams[i].name);
							$('#stream-owner').html(streams[i].ownerName);
							$('#stream-location').html(streams[i].location);

							var xhttp;
							xhttp = new XMLHttpRequest();
							xhttp.onreadystatechange = function() {
								if (this.readyState == 4 && this.status == 200) {
									myFunction(this);
								}
							};
							xhttp.open("GET", "xmlFiles/" + streams[i].name
									+ ".xml", true);
							xhttp.send();

							function myFunction(xml) {
								var x, i, txt, xmlDoc;
								xmlDoc = xml.responseXML;
								txt = "";
								x = xmlDoc.getElementsByTagName("attribute");
								str = '';
								for (i = 0; i < x.length; i++) { /* 
								        txt += x[i].childNodes[0].nodeValue + "<br>";
								 */
									var val = x[i].childNodes[0].nodeValue;
								 console.log(val);	
								 var idVal = val.substring(0, 4);
									str = str
											+ ' <label class="form-check-label">'
											+ '<input type="checkbox"  value="'+val+'" id="'+val+'" class="form-check-input" name="t">'
											+ val + '</label><br>';
								}
								document.getElementById("list-attributes").innerHTML = str;
						}
						}

					});

	function streamInfoOpen() {
		$('#div-streamInfo').css({
			"display" : "initial"
		});
	}
	function streamInfoClose() {
		$('#div-streamInfo').css({
			"display" : "none"
		});
	}

	function Rename() {

		var idd = $('#select-stream').val();

	}
