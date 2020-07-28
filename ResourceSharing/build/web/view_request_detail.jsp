<%-- 
    Document   : view_request_detail
    Created on : Jul 3, 2020, 4:47:55 PM
    Author     : Yun
--%>
<%@taglib uri="/struts-tags" prefix="s"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Request Detail</title>
        <link rel="stylesheet" type="text/css" href="resource/vendor/bootstrap/css/bootstrap.min.css">
        <!--===============================================================================================-->
        <link rel="stylesheet" type="text/css" href="resource/fonts/font-awesome-4.7.0/css/font-awesome.min.css">
        <script src="resource/vendor/bootstrap/js/bootstrap.min.js"></script>
        <script src="resource/vendor/jquery/jquery-3.2.1.min.js"></script>
    </head>
    <body>
        <section class="jumbotron text-center">
            <div class="container">
                <h1 class="jumbotron-heading">REQUEST DETAIL</h1>
            </div>
        </section>

        <div class="container mb-4">
            <h1>Detail for Request ID : <span style="color: red"><s:property value="#parameters.requestID" /></span></h1> <br/><br/>



            <button onclick="goBack()" class="btn btn-success">Back </button> <br/><br/>

            <table class="table">
                <thead class="thead-dark">
                    <tr>
                        <th>No.</th>
                        <th>Resource ID</th>
                        <th>Resource Name</th>
                        <th>Quantity</th>
                    </tr>
                </thead>
                <tbody>
                    <s:iterator value="#request.listRequestDetail" status="counter">
                        <s:form  theme="simple">
                            <tr>
                                <td>
                                    <s:property value="%{#counter.count}" />
                                </td>
                                <td> <s:property value="resourceID" /></td>
                                <td>
                                    <s:property value="resoureName" />
                                </td>
                                <td> 
                                    <s:property value="quantity" />
                                </td>

                            </tr>
                        </s:form>
                    </s:iterator>

                </tbody>
            </table>

        </div>
        <script>
            function goBack() {
                window.history.back();
            }

        </script>
    </body>
</html>
