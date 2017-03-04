/**
 * Created by andrea on 04/03/2017.
 */
$(document).ready(function(){
    callAjax();

});

function callAjax() {

    $.ajax({
        url : '/welcome/dashboard',
        success : function(response) {
            var v = 0;
            var res1 = null;
            var s21 = null;
            // var ipad1 = null;
            // var itouch1 = null;
            var jsonType = JSON.parse(response);
            $.each(jsonType, function(key,value)
                {
                    if (v == 0)
                    {

                        res1 = value;
                        v++;

                    }
                    else if(v == 1)
                    {
                        s21 = value;
                        v++;

                    }
                    // else if(v == 2)
                    // {
                    //     ipad1 = value;
                    //     v++;
                    //
                    // }
                    // else if(v == 3)
                    // {
                    //     itouch1 = value;
                    //     v++;
                    //
                    // }
                }

            );

            var data = [];
            for (var i = 0; i < 10; i++) {

                var x = {
                    res: res1[i],
                    s2: s21[i],
                    // ipad: ipad1[i],
                    // itouch: itouch1[i]
                };
                data.push(x);

            }



            Morris.Area({
                element: 'morris-area-chart',
                data: data,
                xkey: 'res',
                ykeys: 's2',
                labels: 'MD S2',
                // ykeys: ['iphone', 'ipad', 'itouch'],
                // labels: ['iPhone', 'iPad', 'iPod Touch'],
                pointSize: 2,
                hideHover: 'auto',
                resize: true
            });

        }
    });
}