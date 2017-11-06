
$(document).ready(function() {
	
    //Stops the submit request
    $("#login").submit(function(e){
           e.preventDefault();
    });
	
    //checks for the button click event
    $("#submitPass,#submitAnswer").click(function(e){
         if($(e.target).attr('id')=='submitPass'){  
            //get the form data using another method 
            var userName = $("input#userName").val(); 
            var userPass = $("input#userPassword").val();
            var ipAddr = $("input#ipAddress").val();
            var macAddr = $("input#macAddress").val();
            
            var s = {
            		userName: userName,
            		userPass: userPass,
            		ipAddr:   ipAddr,
            		macAddr:  macAddr,
            };
            
            //make the AJAX request, dataType is set to json
            //meaning we are expecting JSON data in response from the server
            $.ajax({
                type: "POST",
                url: "UserInformation",
                data: s,
                dataType: "json",
                
                //if received a response from the server
                success: function( data, textStatus, jqXHR) {
                    //our country code was correct so we have some information to display
                	if(data.success){
                    	 $("#passResponse").html("<div><b>Passed!</b></div>");

                    	 $("#barrierResponse").html("<b>Please enter: " + data.barrier+"</b>");
                    	 
                    	 $("#ajaxResponse").html("");
                    	 $("#ajaxResponse").append("<b>IP:</b> " + data.user.ipAddr + "   <b>Zipcode:</b>"+data.user.zipcode
                    			 + "   <b>City:</b>"+data.user.city + "   <b>State:</b>"+data.user.state
                    			 + "   <b>Country:</b>"+data.user.country + "<br>");
                    	 $("#ajaxResponse").append("<b>Mac:</b>"+data.user.mac + "   <b>Weekday:</b>"+data.user.weekday
                    			 + "   <b>Time:</b>"+data.user.timeFrame + "<br>");
                    	 $("#ajaxResponse").append("<b>Location Score:</b>"+data.locationScore + "<br>");
                    	 $("#ajaxResponse").append("<b>Mac Score:</b>"+data.macScore + "<br>");
                    	 $("#ajaxResponse").append("<b>Weekday Score:</b>"+data.dayScore + "<br>");
                    	 $("#ajaxResponse").append("<b>Time Score:</b>"+data.timeScore + "<br>");
                    	 $("#ajaxResponse").append("<b>FinalScore:</b>"+data.finalScore + "<br>");

                     } 
                     //display error message
                     else {
                    	 $("#passResponse").html("<div><b>Failed!</b></div>");
                    	 $("#barrierResponse").html("");
                         $("#ajaxResponse").html("<div><b>User ID or password in Invalid!</b></div>");
                     }
                },
                
                //If there was no resonse from the server
                error: function(jqXHR, textStatus, errorThrown){
                     console.log("Something really bad happened " + textStatus);
                      $("#ajaxResponse").html(jqXHR.responseText);
                },
                
                //capture the request before it was sent to server
                beforeSend: function(jqXHR, settings){
                    //adding some Dummy data to the request
                    settings.data += "&dummyData=whatever";
                    //disable the button until we get the response
                    $('#submitPass').attr("disabled", true);
                },
                
                //this is called after the response or error functions are finsihed
                //so that we can take some action
                complete: function(jqXHR, textStatus){
                    //enable the button 
                    $('#submitPass').attr("disabled", false);
                }
      
            }); 
         } else if ($(e.target).attr('id')=='submitAnswer'){
        	//get the form data using another method 
             var userName = $("input#userName").val(); 
             var userPass = $("input#userPassword").val();
//             var userAnswer = $('input[name="answer"]:checked', '#login').val();
             var userAnswer = $('input[name="answer"]:checked').val();
             
             var s = {
             		userName: userName,
             		userPass: userPass,
             		userAnswer: userAnswer,
             };
             console.log("userAnswer = "+userAnswer+" userId = "+userName);
             //make the AJAX request, dataType is set to json
             //meaning we are expecting JSON data in response from the server
             $.ajax({
                 type: "POST",
                 url: "UserAnswer",
                 data: s,
                 dataType: "json",
                 
                 //if received a response from the server
                 success: function( data, textStatus, jqXHR) {
                     //our country code was correct so we have some information to display
                 	if(data.success){
                     	 $("#passResponse").html("<div><b>Passed!</b></div>");
                     	 $("#barrierResponse").html("<b>Successfully Login!!</b>");
                     	 $("#ajaxResponse").html("<div><b>Uswer Answer is correct!</b></div>");

                      } 
                      //display error message
                      else {
                     	 $("#passResponse").html("<div><b>Failed!</b></div>");
                         $("#ajaxResponse").html("<div><b>User Answer is wrong!</b></div>");
                      }
                 },
                 
                 //If there was no resonse from the server
                 error: function(jqXHR, textStatus, errorThrown){
                      console.log("Something really bad happened " + textStatus);
                       $("#ajaxResponse").html(jqXHR.responseText);
                 },
                 
                 //capture the request before it was sent to server
                 beforeSend: function(jqXHR, settings){
                     //adding some Dummy data to the request
                     settings.data += "&dummyData=whatever";
                     //disable the button until we get the response
                     $('#submitPass').attr("disabled", true);
                 },
                 
                 //this is called after the response or error functions are finsihed
                 //so that we can take some action
                 complete: function(jqXHR, textStatus){
                     //enable the button 
                     $('#submitPass').attr("disabled", false);
                 }
       
             });
         }
    });
});