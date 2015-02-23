<!DOCTYPE html>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Login</title>
</head>
<body>

<script src="js/jquery-1.8.2.min.js"></script>
<script src="js/javarest.js"></script>
<script src="js/user.js"></script>

<script>
    $(document).ready(function () {

        window.fbAsyncInit = function() {
            FB.init({
                appId      : '514119888727245',
                xfbml      : true,
                version    : 'v2.2'
            });

            $('.fb').on('click', function () {

                FB.login(function(response) {
                    console.log(response)
                    if (response.authResponse) {
                        javaRest.user.loginSocial(response.authResponse.accessToken, function (error) {
                            if (error)
                                console.log(error)
                            else
                                window.location = 'dashboard.html'
                        })
                        console.log('Welcome!  Fetching your information.... ');
                        FB.api('/me', function(response) {
                            console.log('Good to see you, ' + response.name + '.');
                        });
                    } else {
                        console.log('User cancelled login or did not fully authorize.');
                    }
                });

            })
        };

        (function(d, s, id){
            var js, fjs = d.getElementsByTagName(s)[0];
            if (d.getElementById(id)) {return;}
            js = d.createElement(s); js.id = id;
            js.src = "//connect.facebook.net/en_US/sdk.js";
            fjs.parentNode.insertBefore(js, fjs);
        }(document, 'script', 'facebook-jssdk'));

    });
</script>


<div class="fb">Login with Facebook</div>

</body>
</html>