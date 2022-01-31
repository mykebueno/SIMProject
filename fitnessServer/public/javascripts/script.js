$(document).ready(function() {

  $("#loginSubmit").click(function(){
      console.log("Login -> Dashboard - A preparar request");

      var username = $("#floatingInput").val();
      var password = $("#floatingPassword").val();

      console.log(username);
      console.log(password);

      $.ajax({
        url: "http://localhost:3000/dashboard",
        type: "GET",
        crossDomain: true,
        headers: {
          "Authorization": "Basic " + btoa(username + ":" + password)
        },
        success: function (result){
          alert('Get request went through!');
          $("html").html(result);
          window.location.href = "http://localhost:3000/dashboard";
        },
        error: function (textStatus, errorThrown) {
          alert("Wrong password or email address!");
        }
      });
  });

  $("#registerProceed").click(function(){
    console.log("Login -> Register - A preparar request");

    $.ajax({
      url: "http://localhost:3000/register",
      type: "GET",
      crossDomain: true,
      success: function (result){
        alert('Get request went through!');
        $("html").html(result);
        window.location.href = "http://localhost:3000/register";
      },
      error: function (textStatus, errorThrown) {
        alert("Wrong password or email address!");
      }
    });
  });

  $("#registerSubmit").click(function(){
    console.log("Register -> Login - A preparar request");

    var username = $("#exampleInputUsername").val();
    var email = $("#exampleInputEmail1").val();
    var password = $("#exampleInputPassword1").val();
    var phone = $("#exampleInputPhone").val();

    var sendInfo =  {"username": username, "password": password, "email": email, "phone": phone};

    $.ajax({
      url: "http://localhost:3000/userRegister",
      type: "POST",
      dataType: 'json',
      processData: false,
      contentType: 'application/json',
      data: JSON.stringify(sendInfo),
      success: function (result){
        alert('Get request went through!');
        console.log(result);
      },
      error: function (textStatus, errorThrown) {
        alert("Wrong password or email address!");
      }
    });

  });

});

