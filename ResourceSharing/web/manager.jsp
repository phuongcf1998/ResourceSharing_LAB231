<%-- 
    Document   : user
    Created on : Jun 27, 2020, 5:46:25 PM
    Author     : Yun
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="/struts-tags" prefix="s"%>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Manager Page</title>
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
                <a class="btn btn-success mbutton" href="initStatus"><h3>Process Request</h3></a>

            </div>


            <br/><br/>

        </div>

        <div style="margin-left: 100px;">
            <h2>Search Resource </h2> <br/>
            <form  action="searchResourceManager">
                <div class="form-row">
                    <div class="col-2">
                        <input type="text" name="name" class="form-control" placeholder="Resource Item Name" value="<s:property value="%{#parameters.name}"/>">
                    </div>
                    <div class="col-2">
                        <select name="category" id="p1" class="form-control">
                            <s:if test="%{#request.listCategory!=null}">
                                <s:iterator var="cat" value="#request.listCategory">
                                    <option value="<s:property value="categoryID"/>"<s:if test="%{category == #cat.categoryID }">selected</s:if>>
                                        <s:property value="description"/>
                                    </option>

                                </s:iterator>

                            </s:if>
                            <s:if test="%{#request.listCategory == null}">
                                <option value="1">Computers</option>
                                <option value="2">Supplies</option>
                                <option value="3">Office Equipment</option>
                                <option value="4">Smart Phone</option>
                            </s:if>

                        </select>
                    </div>
                    <div class="col-2">
                        <input type="date" name="fromDate" required class="form-control" value="<s:property value="%{#parameters.fromDate}"/>" placeholder="From Date">
                    </div>
                    <div class="col-2">
                        <input type="date" name="toDate" required class="form-control" value="<s:property value="%{#parameters.toDate}"/>" placeholder="To Date">
                    </div>
                    <div class="form-row col-2" style="margin-left: 50px">
                        <input type="submit" class="form-control btn btn-primary" style="font-size: 20px" value="Search" name="btAction">
                    </div>
                </div>
            </form> <br/><br/>


            <s:if test="%{#request.listResource != null && #request.totalPage!=null}">
                <h3 style=" display: flex;justify-content: center;margin-bottom: 10px">Search Result</h3> <br><br>

                <div class="center">
                    <s:iterator  begin="1" end="%{#request.totalPage}" status="counter">
                        <s:url action="searchResourceManager" id="currentPage" >  
                            <s:param name="name"><s:property value="%{#parameters.name}"/></s:param>
                            <s:param name="category"><s:property value="%{#parameters.category}"/></s:param>
                            <s:param name="fromDate"><s:property value="%{#parameters.fromDate}"/></s:param>
                            <s:param name="toDate"><s:property value="%{#parameters.toDate}"/></s:param>
                            <s:param name="page"><s:property value="%{#counter.count}"/></s:param>
                        </s:url>
                        <s:a cssStyle="margin:5px" id="%{#counter.count}" href="%{currentPage}"><s:property value="%{#counter.count}" /></s:a>
                    </s:iterator>

                </div> <br/>
                <br/>
                <table class="table">
                    <thead class="thead-dark">
                        <tr>
                            <th>No.</th>
                            <th>Name</th>
                            <th>Category</th>
                            <th>Color</th>
                            <th>Quantity</th>
                            <th>From Date</th>
                            <th>To Date</th>

                        </tr>
                    </thead>
                    <tbody>
                        <s:iterator value="#request.listResource" status="counter">
                            <s:form action="update" theme="simple">
                                <tr>
                                    <td>
                                        <s:property value="%{#counter.count}" />
                                    </td>
                                    <td>
                                        <s:property value="name" />
                                    </td>
                                    <td>
                                        <s:property  value="categoryID"  />
                                    </td>
                                    <td>
                                        <s:property value="color"  />
                                    </td>

                                    <td>
                                        <s:if test="%{quantity == 0}">
                                            <span style="color: red">Out of stock</span>
                                        </s:if>
                                        <s:else>
                                            <s:property value="quantity" />
                                        </s:else>
                                    </td>
                                    <td>
                                        <s:property  value="from_date"  />
                                    </td>
                                    <td>
                                        <s:property value="to_date" />
                                    </td>


                                </tr>
                            </s:form>
                        </s:iterator>

                    </tbody>
                </table>


            </s:if>

            <s:if test="%{#request.listResource == null && #request.totalPage!=null}">
                <h1>No record is matched !!! </h1>
            </s:if>
        </div>   




        <script>
            document.getElementById("<s:property value="%{#parameters.page}"/>").style.color = 'red';


        </script>
    </body>
</html>
