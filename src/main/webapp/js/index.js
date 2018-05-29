$(document).ready(function () {
    $.ajax({
        type: "GET",
        url: "route",
        dataType: "json",
        success: function (responseJson) {
            var c=document.getElementById("myCanvas");
            var ctx=c.getContext("2d");
            ctx.strokeStyle = "rgb(255,0,0)"
            ctx.lineWidth = 4;
            // ctx.setLineDash([20,5]);
            ctx.beginPath();
            $.each(responseJson, function (index, coordinate) {
                if (index == 0) {
                    ctx.arc(coordinate.xAxis, coordinate.yAxis,3,0,2*Math.PI);
                    ctx.moveTo(coordinate.xAxis,coordinate.yAxis);
                } else {
                    ctx.lineTo(coordinate.xAxis,coordinate.yAxis);
                    ctx.arc(coordinate.xAxis, coordinate.yAxis,3,0,2*Math.PI);
                }
            });
            ctx.stroke();
            ctx.closePath();
        },
        error: function (responseJson) {
            alert("Failure : " + responseJson.status);
        }
    });
});