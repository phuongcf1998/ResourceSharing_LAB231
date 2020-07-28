<%-- 
    Document   : view_cart
    Created on : Jun 30, 2020, 3:58:06 PM
    Author     : Yun
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="/struts-tags" prefix="s"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>View Cart Page</title>
        <link rel="stylesheet" type="text/css" href="resource/vendor/bootstrap/css/bootstrap.min.css">
        <!--===============================================================================================-->
        <link rel="stylesheet" type="text/css" href="resource/fonts/font-awesome-4.7.0/css/font-awesome.min.css">
        <script src="resource/vendor/bootstrap/js/bootstrap.min.js"></script>
        <script src="resource/vendor/jquery/jquery-3.2.1.min.js"></script>

        <style>
            .total {
                display: flex;
                justify-content: flex-end
            }
        </style>

    </head>
    <body>




        <s:if test="%{#session.CART.items!=null}">
            <section class="jumbotron text-center">
                <div class="container">
                    <h1 class="jumbotron-heading">RESOURCE CART</h1>
                </div>
            </section>

            <div class="display: flex;flex-direction: row;">
                <a class="btn btn-block mbutton" href="user.jsp">Continue Book</a>
            </div>

            <div class="container mb-4">
                <div class="row">
                    <div class="col-12">
                        <div class="table-responsive">
                            <table class="table table-striped">
                                <thead>
                                    <tr>
                                        <th scope="col">No.</th>
                                        <th scope="col">Image</th>
                                        <th scope="col">Resource</th>
                                        <th scope="col">Available</th>
                                        <th scope="col" class="text-center">Quantity</th>
                                        <th>Action</th>
                                        <th>Action</th>
                                    </tr>
                                </thead>
                                <tbody>
                                    <s:set var="total" value="0"/>
                                    <s:iterator var="item" value="%{#session.CART.items}" status="counter">
                                    <form action="updateCart" onsubmit="return check(this,<s:property value="%{#item.key.quantity}" />)">
                                        <tr>
                                            <td>
                                                <s:property value="%{#counter.count}" />
                                            </td>
                                            <td><img src="resource/images/resource_img.png" /> </td>
                                            <td>
                                                <s:property value="%{#item.key.name}" />
                                            </td>
                                            <td> 
                                                <s:property value="%{#item.key.quantity}" />
                                            </td>
                                            <td>
                                                <input class="form-control" name="quantity" type="number" value="<s:property value="%{#item.value}" />" />
                                            </td>
                                            <td>
                                                <input type="submit" class="btn btn-success" value="Update" />
                                                <input type="hidden" name="resourceID"  value="<s:property value="%{#item.key.resourceID}"/>"/>

                                            </td>
                                            <td>
                                                <s:url action="remove" id="removeLink" >
                                                    <s:param name="resourceID"><s:property value="%{#item.key.resourceID}"/></s:param>

                                                </s:url>
                                                <s:a cssClass="btn btn-danger removeItem"  onclick="return confirm('Are you sure to remove ?')" href="%{removeLink}">Remove</s:a>



                                                <s:set var="total" value="%{#total+#item.value}"/>  
                                            </td>
                                        </tr>

                                    </form>
                                </s:iterator>
                                <tr>
                                    <td></td>
                                    <td></td>
                                    <td></td>
                                    <td></td>
                                    <td></td>
                                    <td></td>
                                    <td>
                                        Total quantity : <span style="color: red;font-size: 20px"><s:property value="%{#total}"/></span>

                                    </td>

                                </tr>
                                </tbody>
                            </table>



                        </div>
                    </div>
                    <div class="col mb-2">


                        <form  action="book" method="POST">
                            <div class="form-row">
                                <div class="col-6">
                                    <input type="text" style="width: 100%" required name="requestName" placeholder="Request Name"/> <br/>
                                    <input type="hidden" name="totalQuantity" value="<s:property value="%{#total}"/>"/>
                                </div>

                                <div class="col-6">
                                    <input style="width: 100%;background-color: greenyellow" onclick="return confirm('Are you sure to book ?')" type="submit" value="Book" />
                                </div>

                            </div>
                        </form>






                    </div> <br/>



                </div>
            </div>
        </div>
    </s:if>

    <s:if test="%{#session.CART==null or #session.CART.items==null}">
        <section class="jumbotron text-center">
            <div class="container">
                <h1 class="jumbotron-heading">RESOURCE CART</h1>
            </div>
        </section>
        <div style="display: flex;flex-direction: column;justify-content: center;align-items: center">
            <h1>No item in your cart !</h1>


            <div>
                <button class="btn btn-block btn-light"><a href="initCategory">Continue Shopping</a></button>
            </div>
        </div>



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

        function check(form, quantityAvailable) {
            if (form.quantity.value > quantityAvailable) {
                window.alert("Quantity must <= " + quantityAvailable);
                return false
            }
            if (form.quantity.value <= 0) {
                window.alert("Quantity invalid ");
                return false
            }

        }
    </script>
</body>
</html>
