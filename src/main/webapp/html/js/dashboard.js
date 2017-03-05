/**
 * Created by andrea on 04/03/2017.
 */
$(document).ready(function(){
    callAjax();

});

function callAjax() {

    $.ajax({
        url : '/service/dashboard',
        success : function(response) {
            var v = 0;
            var res1 = null;
            var s21 = null;

            var jsonType = JSON.parse(response);
            var count = jsonType.res.length;
            console.log(count);
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
                }
            );

            var data = [];
            for (var i = 0; i < count; i++) {
                var x = {
                    res: res1[i],
                    s2: s21[i],
                };
                data.push(x);

            }

            Morris.Line({
                element: 'morris-line-chart',
                data: data,
                xkey: 'res',
                ykeys: ['s2'],
                labels: ['MD S2'],
                pointSize: 2,
                hideHover: 'auto',
                resize: true
            });

        }
    });
}