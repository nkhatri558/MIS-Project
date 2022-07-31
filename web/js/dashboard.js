
$(function () {
    var registeredStudentsCount = 0;
    var unRegisteredStudentsCount = 0;
    var seminarAppovedCount = 0;
    var seminarRejectedCount = 0;
    var paidFeesCount = 0;
    var unPaidFeesCount = 0;
    var maleCount=0;
    var femaleCount=0;
    $.ajax({
        url: "DashboardChartController",
        method: "POST",
        async: false,
        data: {
            action: "view"
        },
        success: function (data) {

            var data = JSON.parse(data);
            registeredStudentsCount = data[0].registeredStudentsCount;
            unRegisteredStudentsCount = data[1].unRegisteredStudentsCount;
            seminarAppovedCount = data[2].seminarApproved;
            seminarRejectedCount = data[3].seminarRejected;
            paidFeesCount = data[4].feesPaidCount;
            unPaidFeesCount = data[5].feesUnPaidCount;
            maleCount=data[6].maleCount;
            femaleCount=data[7].femaleCount;
        }
    });
    /* ChartJS
     * -------
     * Here we will create a few charts using ChartJS
     */

    //--------------
    //- AREA CHART -
    //--------------

    // Get context with jQuery - using jQuery's .get() method.
    var areaChartCanvas = $('#lineChart').get(0).getContext('2d');
    
        var areaChartData = {
        labels: [
            'Non Registered',
            'Registered'
        ],
        datasets: [
            {
                data: [unRegisteredStudentsCount, registeredStudentsCount],
                backgroundColor: ['#f56954', '#238C9A', '#f39c12', '#00c0ef', '#3c8dbc', '#d2d6de'],
            }
            
        ]
    }

    var areaChartOptions = {
        maintainAspectRatio: false,
        responsive: true,
        legend: {
            display: false
        },
        scales: {
            xAxes: [{
                    gridLines: {
                        display: false,
                    }
                }],
            yAxes: [{
                    gridLines: {
                        display: false,
                    }
                }]
        }
    }

// This will get the first returned node in the jQuery collection.
    var areaChart = new Chart(areaChartCanvas, {
        type: 'line',
        data: areaChartData,
        options: areaChartOptions
    })

    //-------------
    //- LINE CHART -
    //--------------
    var lineChartCanvas = $('#lineChart').get(0).getContext('2d')
    var lineChartOptions = jQuery.extend(true, {}, areaChartOptions)
    var lineChartData = jQuery.extend(true, {}, areaChartData)

    var lineChart = new Chart(lineChartCanvas, {
        type: 'line',
        data: lineChartData,
        options: lineChartOptions
    })

    //-------------
    //- DONUT CHART -
    //-------------
    // Get context with jQuery - using jQuery's .get() method.
    var donutChartCanvas = $('#donutChart').get(0).getContext('2d')
    var donutData = {
        labels: [
            'Rejected',
            'Approved'
        ],
        datasets: [
            {
                data: [seminarRejectedCount, seminarAppovedCount],
                backgroundColor: ['#f56954', '#00a65a', '#f39c12', '#00c0ef', '#3c8dbc', '#d2d6de'],
            }
        ]
    }
    var donutOptions = {
        maintainAspectRatio: false,
        responsive: true,
    }
//Create pie or douhnut chart
// You can switch between pie and douhnut using the method below.
    var donutChart = new Chart(donutChartCanvas, {
        type: 'doughnut',
        data: donutData,
        options: donutOptions
    })


    //-------------
    //- BAR CHART -
    //-------------
    var barChartCanvas = $('#barChart').get(0).getContext('2d')
    var barData={
        
        datasets: [
            {
                label: 'Paid',
                backgroundColor: 'rgba(60,141,188,0.9)',
                borderColor: 'rgba(60,141,188,0.8)',
                pointRadius: false,
                pointColor: '#3b8bba',
                pointStrokeColor: 'rgba(60,141,188,1)',
                pointHighlightFill: '#fff',
                pointHighlightStroke: 'rgba(60,141,188,1)',
                data: [paidFeesCount]
            },
            {
                label: 'Unpaid',
                backgroundColor: 'rgba(210, 214, 222, 1)',
                borderColor: 'rgba(210, 214, 222, 1)',
                pointRadius: false,
                pointColor: 'rgba(210, 214, 222, 1)',
                pointStrokeColor: '#c1c7d1',
                pointHighlightFill: '#fff',
                pointHighlightStroke: 'rgba(220,220,220,1)',
                data: [unPaidFeesCount]
            },
            
        ]
    }
    var barChartData = jQuery.extend(true, {},barData)
    var temp0 = barData.datasets[0]
    var temp1 = barData.datasets[1]
    barChartData.datasets[0] = temp1
    barChartData.datasets[1] = temp0

    var barChartOptions = {
        responsive: true,
        maintainAspectRatio: false,
        datasetFill: false
    }

    var barChart = new Chart(barChartCanvas, {
        type: 'bar',
        data: barChartData,
        options: barChartOptions
    });
    
    //-------------
    //- PIE CHART -
    //-------------
    // Get context with jQuery - using jQuery's .get() method.
    var pieChartCanvas = $('#pieChart').get(0).getContext('2d')
    var pieData = {
        labels: [
            'Male',
            'Female'
        ],
        datasets: [
            {
                data: [maleCount, femaleCount],
                backgroundColor: ['#f56954', '#00a65a', '#f39c12', '#00c0ef', '#3c8dbc', '#d2d6de'],
            }
        ]
    }
    
    
    var pieData        = pieData;
    var pieOptions     = {
      maintainAspectRatio : false,
      responsive : true,
    }
    //Create pie or douhnut chart
    // You can switch between pie and douhnut using the method below.
    var pieChart = new Chart(pieChartCanvas, {
      type: 'pie',
      data: pieData,
      options: pieOptions      
    })

})