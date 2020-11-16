(function() {
    //alert("iouo");
    $("form").addEventListener("submit", function(e) {

        e.preventDefault();
    })
})();

function saveToken() {
    /*$(function() {
        $("#btnlogin").click(function () {*/
    //alert("JS working")
            console.log("JS started");
            const form = document.querySelector('form')
    /*const ul = document.querySelector('ul')
    const button = document.querySelector('button')
    const input = document.getElementById('item')
*/
            var username = document.getElementById("username").value;
            var password = document.getElementById("password").value;

          /*  var username = $("#username").val();
            var password = $("#password").val();*/


            form.addEventListener('submit', function (e) {

                console.log("Submit event started");

                /* e.preventDefault()

                itemsArray.push(input.value)*/

                let data = JSON.stringify({"username" : username , "password" : password});

                console.log(data);
                //alert(data);

                localStorage.setItem('jwtToken', data.accessToken);

                /*liMaker(input.value)
                input.value = ''*/
            });


            /* button.addEventListener('click', function () {
                 localStorage.clear()
                 while (ul.firstChild) {
                     ul.removeChild(ul.firstChild)
                 }
             })*/

           /* $.ajax({
                cache: true,
                type: "POST",
                url: "/api/auth/signin",
                contentType: "application/json;charset=UTF-8",
                data:JSON.stringify({"username" : username, "password" : password}),
                dataType: "json",
                async: false,
                error: function (request) {
                    alert(request);
                    console.log("Connection error");
                },
                success: function (data) {
                    console.log(data);

                    //save token
                    localStorage.setItem("token",data.accessToken);


                }
            });*/
   /*     });
    });*/
}

