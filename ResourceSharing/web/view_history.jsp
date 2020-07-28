<%-- 
   Document   : view_history
   Created on : Jul 2, 2020, 3:25:02 PM
   Author     : Yun
--%>
<%@taglib uri="/struts-tags" prefix="s"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" type="text/css" href="resource/vendor/bootstrap/css/bootstrap.min.css">
        <!--===============================================================================================-->
        <link rel="stylesheet" type="text/css" href="resource/fonts/font-awesome-4.7.0/css/font-awesome.min.css">
        <script src="resource/vendor/bootstrap/js/bootstrap.min.js"></script>
        <script src="resource/vendor/jquery/jquery-3.2.1.min.js"></script>

        <title>History Page</title>
    </head>
    <body>
        <section class="jumbotron text-center">
            <div class="container">
                <h1 class="jumbotron-heading">REQUEST HISTORY</h1>
            </div>
        </section>

        <div class="container mb-4">
            <a class="btn btn-success" href="initCategory">Back Home</a> <br/> <br/><br/>

            <form  action="searchRequest">
                <div class="form-row">
                    <div class="col-4">
                        <input type="text" name="resourceName" class="form-control" placeholder="Request Name" value="<s:property value="#parameters.resourceName" />">
                    </div>

                    <div class="col-2">
                        <input type="date" name="requestDate" required class="form-control" value="<s:property value="#parameters.requestDate" />" placeholder="Request Date">
                        <input type="hidden" name="userID" value="<s:property value="%{#session.USER.userID}"/>">
                    </div>
                    <div class="form-row col-2" style="margin-left: 50px">
                        <input type="submit" class="form-control btn btn-primary" style="font-size: 20px" value="Search Request">
                    </div>
                </div>
            </form> <br/><br/>
        </div>
        <s:if test="%{#request.listSearchRequestHistory != null && #request.totalPage!=null}">
            <div class="container mb-4">
                <table class="table">
                    <thead class="thead-dark">
                        <tr>
                            <th scope="col">No.</th>

                            <th scope="col">Request ID</th>
                            <th scope="col">Request Name</th>
                            <th scope="col">Book Date</th>
                            <th scope="col">Quantity</th>
                            <th scope="col">Status</th>
                            <th scope="col" >Detail</th>
                            <th scope="col">Action</th>



                        </tr>
                    </thead>
                    <tbody>
                        <s:iterator value="#request.listSearchRequestHistory" status="counter">

                            <tr>
                                <td>
                                    <s:property value="%{#counter.count}" />
                                </td>
                                <td> <s:property value="requestID" /></td>
                                <td> <s:property value="requestName" /></td>
                                <td>
                                    <s:property value="request_date" />
                                </td>
                                <td> 
                                    <s:property value="total" />
                                </td>
                                <td> 
                                    <s:if test="%{status == 1}">
                                        <div class="btn btn-success">
                                            Requested
                                        </div>
                                    </s:if>
                                    <s:if test="%{status == 3}">
                                        <div class="btn btn-warning">
                                            Cancel
                                        </div>
                                    </s:if>
                                    <s:if test="%{status == 4}">
                                        <div class="btn btn-primary">
                                            Accept
                                        </div>
                                    </s:if>
                                    <s:if test="%{status == 5}">
                                        <div class="btn btn-danger">
                                            Not approved
                                        </div>
                                    </s:if>
                                </td>
                                <td>
                                    <s:url action="viewDetail" id="viewDetail" >
                                        <s:param name="requestID"><s:property value="requestID"/></s:param>

                                    </s:url>
                                    <s:a href="%{viewDetail}">View Detail</s:a>
                                    </td>
                                    <td>
                                    <s:if test="%{status == 1}">
                                        <s:url action="cancelRequest" id="cancelLink" >
                                            <s:param name="requestID"><s:property value="requestID"/></s:param>

                                        </s:url>
                                        <s:a cssClass="btn btn-danger removeItem"  onclick="return confirm('Are you sure to remove ?')" href="%{cancelLink}">Remove</s:a>
                                    </s:if>




                                </td>
                            </tr>

                        </s:iterator>

                    </tbody>
                </table>
                <div class="col mb-2">
                    <div class="row">
                        <div class="col-sm-12  col-md-12 text-right">
                            <a class="btn btn-lg btn-block btn-block btn-info text-uppercase" href="viewHistory">Show All</a>
                        </div>

                    </div>
                </div>
            </div>

        </s:if>
        <s:if test="%{#request.listSearchRequestHistory == null && #request.totalPage !=null}">
            <div class="container mb-4">  <h1>No record is matched !!! </h1> <br/><br/>
                <a class="btn btn-lg btn-block btn-block btn-info text-uppercase" href="viewHistory">Show All</a>
            </div>
        </s:if>
        <s:if test="%{#request.listReq !=null && #request.listSearchRequestHistory ==null}">


            <div class="container mb-4">

                <div class="row">
                    <div class="col-12">
                        <div class="table-responsive">
                            <table class="table table-striped">
                                <thead>
                                    <tr>
                                        <th scope="col">No.</th>

                                        <th scope="col">Request ID</th>
                                        <th scope="col">Request Name</th>
                                        <th scope="col">Book Date</th>
                                        <th scope="col">Quantity</th>
                                        <th scope="col">Status</th>
                                        <th scope="col" >Detail</th>
                                        <th scope="col">Action</th>
                                    </tr>
                                </thead>
                                <tbody>

                                    <s:iterator var="item" value="listReq" status="counter">

                                        <tr>
                                            <td>
                                                <s:property value="%{#counter.count}" />
                                            </td>
                                            <td> <s:property value="requestID" /></td>
                                            <td> <s:property value="requestName" /></td>
                                            <td>
                                                <s:property value="request_date" />
                                            </td>
                                            <td> 
                                                <s:property value="total" />
                                            </td>
                                            <td> 
                                                <s:if test="%{status == 1}">
                                                    <div class="btn btn-success">
                                                        Requested
                                                    </div>
                                                </s:if>
                                                <s:if test="%{status == 3}">
                                                    <div class="btn btn-warning">
                                                        Cancel
                                                    </div>
                                                </s:if>
                                                <s:if test="%{status == 4}">
                                                    <div class="btn btn-primary">
                                                        Accept
                                                    </div>
                                                </s:if>
                                                <s:if test="%{status == 5}">
                                                    <div class="btn btn-danger">
                                                        Not approved
                                                    </div>
                                                </s:if>
                                            </td>
                                            <td>
                                                <s:url action="viewDetail" id="viewDetail" >
                                                    <s:param name="requestID"><s:property value="requestID"/></s:param>

                                                </s:url>
                                                <s:a href="%{viewDetail}">View Detail</s:a>
                                                </td>
                                                <td>
                                                <s:if test="%{status == 1}">
                                                    <s:url action="cancelRequest" id="cancelLink" >
                                                        <s:param name="requestID"><s:property value="requestID"/></s:param>

                                                    </s:url>
                                                    <s:a cssClass="btn btn-danger removeItem"  onclick="return confirm('Are you sure to remove ?')" href="%{cancelLink}">Remove</s:a>

                                                </s:if>




                                            </td>
                                        </tr>


                                    </s:iterator>

                                </tbody>
                            </table>



                        </div>
                    </div>
                    <br/><br/><br/>


                </div>
            </div>
        </s:if>
        <s:if test="%{#request.listReq ==null && #request.totalPage ==null && #request.listSearchRequestHistory ==null }">
            <div class="container mb-4">  <h1>Your History is Empty !</h1></div>

        </s:if>
        <script>
            var removeLinks = document.getElementsByClassName("removeItem");
            for (var i = 0; i < removeLinks.length; i++) {
                removeLinks[i].addEventListener('click', confirmIt, false);
            }
            var confirmIt = function (e) {
                if (!confirm('Are you sure ?'))
                    e.preventDefault();
            };
            function goBack() {
                window.history.back();
            }
        </script>
    </body>
</html>
