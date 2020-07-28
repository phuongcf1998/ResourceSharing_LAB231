<!DOCTYPE html>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="/struts-tags" prefix="s"%>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Create Account</title>

        <!-- Font Icon -->
        <link rel="stylesheet" href="resource/form_create/fonts/material-icon/css/material-design-iconic-font.min.css">

        <!-- Main css -->
        <link rel="stylesheet" href="resource/form_create/css/style.css">

    </head>
    <body>

        <div class="main">

            <!-- Sign up form -->
            <section class="signup">
                <div class="container">
                    <div class="signup-content">
                        <div class="signup-form">
                            <h2 class="form-title">Create Account</h2>
                            <form method="POST" class="register-form" id="register-form" action="createAccount">
                                <div class="form-group">
                                    <label for="name"><i class="zmdi zmdi-account material-icons-name"></i></label>
                                    <input type="text" value="<s:property value="%{#parameters.fullName}"/>"  required name="fullName" id="name" placeholder="Your Name"/>
                                </div>
                                <div class="form-group">
                                    <label for="email"><i class="zmdi zmdi-email"></i></label>
                                    <input type="email" required name="email" id="email" value="<s:property value="%{#parameters.email}"/>" placeholder="Your Email"/>
                                </div>
                                <div class="form-group">
                                    <label for="pass"><i class="zmdi zmdi-lock"></i></label>
                                    <input type="password" value="<s:property value="%{#parameters.password}"/>" required name="password" id="pass" placeholder="Password"/>
                                </div>
                                <div class="form-group">
                                    <label for="re-pass"><i class="zmdi zmdi-lock-outline"></i></label>
                                    <input type="password" value="<s:property value="%{#parameters.confirmPassword}"/>" required name="confirmPassword" id="re_pass" placeholder="Repeat your password"/>
                                </div>
                                <div class="form-group">
                                    <label for="re-pass"><i class="zmdi zmdi-phone"></i></label>
                                    <input type="number" value="<s:property value="%{#parameters.phone}"/>" required name="phone" placeholder="Your phone"/>
                                </div>
                                <div class="form-group">
                                    <label for="re-pass"><i class="zmdi zmdi-archive"></i></label>
                                    <input type="text" value="<s:property value="%{#parameters.address}"/>" required name="address"  placeholder="Your Address"/>
                                </div>

                                <input type="reset" value="Reset"  />

                                <div class="form-group form-button">
                                    <input type="submit" name="signup" id="signup" class="form-submit" value="Create Account"/>
                                    <a href="login.html" class="form-submit">Back</a>
                                </div>
                                <s:if test="%{errorDuplicate !=null}">
                                    <font color="red"> 
                                    <s:property value="Email"  /> is existed !!!
                                    </font>
                                </s:if>

                            </form>
                        </div>
                        <div class="signup-image">
                            <figure><img src="resource/form_create/images/signup-image.jpg" alt="sing up image"></figure>

                        </div>
                    </div>
                </div>
            </section>


        </div>



        <!-- JS -->
        <script>
            var password = document.getElementById("pass")
                    , confirm_password = document.getElementById("re_pass");

            function validatePassword() {
                if (password.value != confirm_password.value) {
                    confirm_password.setCustomValidity("Passwords Don't Match");
                } else {
                    confirm_password.setCustomValidity('');
                }
            }

            password.onchange = validatePassword;
            confirm_password.onkeyup = validatePassword;


        </script>
    </body>
</html>