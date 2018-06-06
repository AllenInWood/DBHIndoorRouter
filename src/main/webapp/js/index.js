$(document).ready(function () {
    $.ajax({
        type: "GET",
        url: "route",
        // get data from tippers interface further
        data: {"startNo": "0", "destinationNo": "3222"},
        dataType: "json",
        success: function (responseJson) {
            if (responseJson.status == "0") {
                var data = responseJson.data;
                for (var key in data) {
                    var c = document.getElementById("myCanvas-" + key);
                    var ctx = c.getContext("2d");
                    ctx.strokeStyle = "rgb(255,0,0)"
                    ctx.lineWidth = 4;
                    ctx.beginPath();
                    $.each(data[key], function (index, coordinate) {
                        if (index == 0) {
                            ctx.arc(coordinate.xAxis, coordinate.yAxis, 3, 0, 2 * Math.PI);
                            ctx.moveTo(coordinate.xAxis, coordinate.yAxis);
                        } else {
                            ctx.lineTo(coordinate.xAxis, coordinate.yAxis);
                            ctx.arc(coordinate.xAxis, coordinate.yAxis, 3, 0, 2 * Math.PI);
                        }
                    });
                    ctx.stroke();
                    ctx.closePath();
                }

            } else {
                alert(responseJson.msg);
            }
        },
        error: function (responseJson) {
            alert("Failure : " + responseJson.status);
        }
    });
});