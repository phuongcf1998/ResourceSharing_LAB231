<%-- 
    Document   : handle_request
    Created on : Jul 17, 2020, 9:26:37 PM
    Author     : HOME
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="/struts-tags" prefix="s"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Process Request Page</title>
        <link rel="stylesheet" type="text/css" href="resource/vendor/bootstrap/css/bootstrap.min.css">
        <!--===============================================================================================-->
        <link rel="stylesheet" type="text/css" href="resource/fonts/font-awesome-4.7.0/css/font-awesome.min.css">

        <style>

            a:active, a:visited {
                color: blue;
            }
            .center {
                display: flex;
                justify-content: center;
            }
            .mbutton{
                margin: 20px;
            }
        </style>
    </head>
    <body>
        <div style="margin-left: 25px">
            <font color="red"><h1>Welcome , <s:property value="%{#session.MANAGER.fullName}"  /></h1> </font> <br/><br/>


            <div style="display: flex;flex-direction: row;">
                <a class="btn btn-primary mbutton" href="logOut"><h3>Log out</h3></a>
                <a class="btn btn-success mbutton" href="initCategory"><h3>Back Home</h3></a>

            </div>


            <br/><br/>

        </div>

        <div style="margin-left: 100px;">
            <h2>Search Request </h2> <br/>

            <form  action="searchManager">
                <div class="form-row">
                    <div class="col-2">
                        <input type="text" name="resourceName" class="form-control" placeholder="Resource Name " value="<s:property value="%{#parameters.resourceName}"/>">
                    </div>
                    <div class="col-2">
                        <input type="text" name="userID" class="form-control" placeholder="User Email" value="<s:property value="%{#parameters.userID}"/>">
                    </div>
                    <div class="col-2">
                        <input type="date" name="requestDate" required class="form-control" value="<s:property value="%{#parameters.requestDate}"/>" placeholder="Request Date">
                    </div>

                    <div class="col-2">
                        <select name="slStatus" id="p1" class="form-control">
                            <s:if test="%{#request.listStatus!=null}">
                                <s:iterator var="sta" value="#request.listStatus">
                                    <option value="<s:property value="status"/>"   <s:if test="%{slStatus == #sta.status }">selected</s:if>>
                                        <s:property value="description"/>
                                    </option>
                                </s:iterator>
                            </s:if>

                            <s:if test="%{#request.listStatus ==null}">
                                <option value="1">New</option>
                                <option value="2">Active</option>
                                <option value="3">InActive</option>
                                <option value="4">Accept</option>
                                <option value="5">Delete</option>
                            </s:if>

                        </select>
                    </div>
                    <div class="form-row col-2" style="margin-left: 50px">
                        <input type="submit" class="form-control btn btn-primary" style="font-size: 20px" value="Search" name="btAction">
                    </div>
                </div>
            </form> <br/><br/>



            <s:if test="%{#request.listSearchRequestForManager != null && #request.totalPage!=null}">
                <h3 style=" display: flex;justify-content: center;margin-bottom: 10px">Search Result</h3> <br><br>

                <div class="center">
                    <s:iterator  begin="1" end="%{#request.totalPage}" status="counter">
                        <s:url action="searchManager" id="currentPage" >
                            <s:param name="resourceName"><s:property value="%{#parameters.resourceName}"/></s:param>
                            <s:param name="userID"><s:property value="%{#parameters.userID}"/></s:param>
                            <s:param name="requestDate"><s:property value="%{#parameters.requestDate}"/></s:param>
                            <s:param name="slStatus"><s:property value="%{#parameters.slStatus}"/></s:param>
                            <s:param name="page"><s:property value="%{#counter.count}"/></s:param>
                        </s:url>
                        <s:a  id="%{#counter.count}" href="%{currentPage}"><s:property value="%{#counter.count}" /></s:a>
                    </s:iterator>

                </div> <br/>

                <table class="table">
                    <thead class="thead-dark">
                        <tr>
                            <th scope="col">No.</th>

                            <th scope="col">Request ID</th>
                            <th scope="col">Request Name</th>
                            <th scope="col">User Email</th>
                            <th scope="col">Book Date</th>
                            <th scope="col">Quantity</th>
                            <th scope="col">Status</th>
                            <th scope="col" >Detail</th>
                            <th scope="col">Action</th>
                            <th scope="col">Action</th>
                        </tr>
                    </thead>
                    <tbody>
                        <s:iterator value="#request.listSearchRequestForManager" status="counter">

                            <tr>
                                <td>
                                    <s:property value="%{#counter.count}" />
                                </td>
                                <td> <s:property value="requestID" /></td>
                                <td> <s:property value="requestName" /></td>
                                <td> <s:property value="userID" /></td>
                                <td>
                                    <s:property value="request_date" />
                                </td>
                                <td> 
                                    <s:property value="total" />
                                </td>
                                <td> 
                                    <s:if test="%{status == 1}">
                                        <div class="btn btn-primary">
                                            New
                                        </div>
                                    </s:if>
                                    <s:if test="%{status == 3}">
                                        <div class="btn btn-warning">
                                            InActive
                                        </div>
                                    </s:if>
                                    <s:if test="%{status == 4}">
                                        <div class="btn btn-success">
                                            Accept
                                        </div>
                                    </s:if>
                                    <s:if test="%{status == 5}">
                                        <div class="btn btn-danger">
                                            Delete
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
                                        <s:url action="processRequest" id="processLink" >
                                            <s:param name="requestID"><s:property value="requestID"/></s:param>
                                            <s:param name="processType">Delete</s:param>
                                            <s:param name="resourceName"><s:property value="%{#parameters.resourceName}"/></s:param>
                                            <s:param name="userID"><s:property value="%{#parameters.userID}"/></s:param>
                                            <s:param name="requestDate"><s:property value="%{#parameters.requestDate}"/></s:param>
                                            <s:param name="slStatus"><s:property value="%{#parameters.slStatus}"/></s:param>
                                            <s:param name="page"><s:property value="%{#counter.count}"/></s:param>
                                        </s:url>
                                        <s:a cssClass="btn btn-danger removeItem"  onclick="return confirm('Are you sure to remove ?')" href="%{processLink}">Delete</s:a>
                                    </s:if>
                                </td>
                                <td>
                                    <s:if test="%{status == 1}">
                                        <s:url action="processRequest" id="processLink" >
                                            <s:param name="requestID"><s:property value="requestID"/></s:param>
                                            <s:param name="processType">Approval</s:param>
                                            <s:param name="resourceName"><s:property value="%{#parameters.resourceName}"/></s:param>
                                            <s:param name="userID"><s:property value="%{#parameters.userID}"/></s:param>
                                            <s:param name="requestDate"><s:property value="%{#parameters.requestDate}"/></s:param>
                                            <s:param name="slStatus"><s:property value="%{#parameters.slStatus}"/></s:param>
                                            <s:param name="page"><s:property value="%{#counter.count}"/></s:param>
                                        </s:url>
                                        <s:a cssClass="btn btn-success removeItem"  onclick="return confirm('Are you sure to approval ?')" href="%{processLink}">Approval</s:a>

                                    </s:if>
                                </td>
                            </tr>

                        </s:iterator>

                    </tbody>
                </table>


            </s:if>

            <s:if test="%{#request.listSearchRequestForManager == null && #request.totalPage!=null}">
                <h1>No record is matched !!! </h1>
            </s:if>
        </div>

        <script>
            document.getElementById("<s:property value="%{#parameters.page}"/>").style.color = 'red';


        </script>
    </body>
</html>
