<%-- 
    Document   : handle_request_error
    Created on : Jul 16, 2020, 9:30:02 AM
    Author     : HOME
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="/struts-tags" prefix="s"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Error Process</title>
        <link rel="stylesheet" type="text/css" href="resource/vendor/bootstrap/css/bootstrap.min.css">
        <!--===============================================================================================-->
        <link rel="stylesheet" type="text/css" href="resource/fonts/font-awesome-4.7.0/css/font-awesome.min.css">
        <script src="resource/vendor/bootstrap/js/bootstrap.min.js"></script>
        <script src="resource/vendor/jquery/jquery-3.2.1.min.js"></script>
        <style>
            .flex-container {
                display: flex;
                height: 300px;
                justify-content: center;
                align-items: center;
            }
        </style>
    </head>
    <body>
        <div class="flex-container">

            <div class="row">
                <div class="col-md-12 text-center">
                    <span class="display-1 d-block">Executed failed !</span> <br/>
                    <div class="mb-4 lead">The request is already executed ! </div>
                    <s:if test="%{#session.USER != null}">
                        <a href="viewHistory" class="btn btn-link">Back to Home</a>
                    </s:if>
                    <s:if test="%{#session.MANAGER != null}">
                        <a href="initStatus" class="btn btn-link">Back to Home</a>
                    </s:if>
                </div>

            </div>
        </div>
    </body>
</html>
