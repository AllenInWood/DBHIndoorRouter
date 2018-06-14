var src;
$(document).ready(function () {
    var beacon_placement = "http://localhost:8080/beacon";
    var beaconID = [];
    $.getJSON("http://sensoria.ics.uci.edu:8059/sensor/get?sensor_type_id=4", function(data) {
        $.each(data, function() {
            beaconID.push(this.id);
        });
        beaconID = new Set(beaconID);
    }).done(function() {
        var d = new Date();
        var timezone = d.getTimezoneOffset();
        d.setTime(Date.now()-timezone*60*1000);
        var now = d.toJSON().replace("T"," ").substring(0, 19);
        d.setTime(Date.now()-300*1000-timezone*60*1000);
        var past = d.toJSON().replace("T"," ").substring(0, 19);
        var url = "http://sensoria.ics.uci.edu:8059/observation/get?start_timestamp=" + past + "&end_timestamp=" + now;
        var recent = new Date(past.replace(/-/g,'/'));
        // var url = "http://sensoria.ics.uci.edu:8059/observation/get?start_timestamp=2018-06-01%2015:10:00&end_timestamp=2018-06-01%2015:20:00";
        // var recent = new Date("2018-06-01 15:10:00".replace(/-/g,'/'));
        var recent_beaconID = "";
        $.getJSON(url, function(data) {
            $.each(data, function() {
                if (this.payload.client_id == "lingxim@ics.uci.edu") {
                    if (beaconID.has(this.sensor_id)) {
                        var t = new Date(this.timestamp.replace(/-/g,'/'));
                        if (t > recent) {
                            recent = t;
                            recent_beaconID = this.sensor_id;
                        }
                    }
                }
            });
        }).done(function() {
            level = parseInt(recent_beaconID[32]);
            major = parseInt(recent_beaconID.substring(recent_beaconID.indexOf("_") + 1, recent_beaconID.length));
            $.getJSON(beacon_placement, function(data) {
                src = data[level-1][recent_beaconID.substring(0, 36)][major-1].room;
                // src = "2222";
            }).done(function () {
                console.log("startNoDone : " + src);
                $('#autocomplete').autocomplete({
                    minChars: 3,
                    lookup: function (query, done) {
                        // console.log("start=" + src);
                        var result;
                        $.ajax({
                            "method": "GET",
                            // "url": "targets",
                            // "data": {"inputRoom": escape(query), "start": src},
                            "url": "targets?inputRoom=" + escape(query) + "&start=" + src,
                            "success": function(responseJson) {
                                result = {
                                    suggestions: responseJson.data
                                };
                                done(result);
                            },
                            "error": function(errorData) {
                                console.log("lookup ajax error");
                                console.log(errorData);
                            }
                        });
                    },
                    onSelect: function (suggestion) {
                        // alert('You selected: ' + suggestion.value + ', ' + suggestion.data);
                        handleSelectSuggestion(src, suggestion);
                    },
                    deferRequestBy: 300
                });

                $('#autocomplete').keypress(function(event) {
                    // keyCode 13 is the enter key
                    if (event.keyCode == 13) {
                        routeIndoortrace(src, $('#autocomplete').val());
                        return false;
                    }
                });

                $('#fulltext').click(function () {
                    routeIndoortrace(src, $('#autocomplete').val());
                });
            });
        });
    });

});

function handleSelectSuggestion(start, suggestion) {
    console.log("start : " + start + ", destination : " + suggestion);
}

function routeIndoortrace(start, destination) {
    $.ajax({
        type: "GET",
        url: "route",
        // get data from tippers interface further
        data: {"startNo": start, "destinationNo": destination},
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
            alert("Failure : " + responseJson.status
                   + "\nInput format wrong!");
        }
    });
}
