/*
   Licensed to the Apache Software Foundation (ASF) under one or more
   contributor license agreements.  See the NOTICE file distributed with
   this work for additional information regarding copyright ownership.
   The ASF licenses this file to You under the Apache License, Version 2.0
   (the "License"); you may not use this file except in compliance with
   the License.  You may obtain a copy of the License at

       http://www.apache.org/licenses/LICENSE-2.0

   Unless required by applicable law or agreed to in writing, software
   distributed under the License is distributed on an "AS IS" BASIS,
   WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
   See the License for the specific language governing permissions and
   limitations under the License.
*/
var showControllersOnly = false;
var seriesFilter = "";
var filtersOnlySampleSeries = true;

/*
 * Add header in statistics table to group metrics by category
 * format
 *
 */
function summaryTableHeader(header) {
    var newRow = header.insertRow(-1);
    newRow.className = "tablesorter-no-sort";
    var cell = document.createElement('th');
    cell.setAttribute("data-sorter", false);
    cell.colSpan = 1;
    cell.innerHTML = "Requests";
    newRow.appendChild(cell);

    cell = document.createElement('th');
    cell.setAttribute("data-sorter", false);
    cell.colSpan = 3;
    cell.innerHTML = "Executions";
    newRow.appendChild(cell);

    cell = document.createElement('th');
    cell.setAttribute("data-sorter", false);
    cell.colSpan = 7;
    cell.innerHTML = "Response Times (ms)";
    newRow.appendChild(cell);

    cell = document.createElement('th');
    cell.setAttribute("data-sorter", false);
    cell.colSpan = 1;
    cell.innerHTML = "Throughput";
    newRow.appendChild(cell);

    cell = document.createElement('th');
    cell.setAttribute("data-sorter", false);
    cell.colSpan = 2;
    cell.innerHTML = "Network (KB/sec)";
    newRow.appendChild(cell);
}

/*
 * Populates the table identified by id parameter with the specified data and
 * format
 *
 */
function createTable(table, info, formatter, defaultSorts, seriesIndex, headerCreator) {
    var tableRef = table[0];

    // Create header and populate it with data.titles array
    var header = tableRef.createTHead();

    // Call callback is available
    if(headerCreator) {
        headerCreator(header);
    }

    var newRow = header.insertRow(-1);
    for (var index = 0; index < info.titles.length; index++) {
        var cell = document.createElement('th');
        cell.innerHTML = info.titles[index];
        newRow.appendChild(cell);
    }

    var tBody;

    // Create overall body if defined
    if(info.overall){
        tBody = document.createElement('tbody');
        tBody.className = "tablesorter-no-sort";
        tableRef.appendChild(tBody);
        var newRow = tBody.insertRow(-1);
        var data = info.overall.data;
        for(var index=0;index < data.length; index++){
            var cell = newRow.insertCell(-1);
            cell.innerHTML = formatter ? formatter(index, data[index]): data[index];
        }
    }

    // Create regular body
    tBody = document.createElement('tbody');
    tableRef.appendChild(tBody);

    var regexp;
    if(seriesFilter) {
        regexp = new RegExp(seriesFilter, 'i');
    }
    // Populate body with data.items array
    for(var index=0; index < info.items.length; index++){
        var item = info.items[index];
        if((!regexp || filtersOnlySampleSeries && !info.supportsControllersDiscrimination || regexp.test(item.data[seriesIndex]))
                &&
                (!showControllersOnly || !info.supportsControllersDiscrimination || item.isController)){
            if(item.data.length > 0) {
                var newRow = tBody.insertRow(-1);
                for(var col=0; col < item.data.length; col++){
                    var cell = newRow.insertCell(-1);
                    cell.innerHTML = formatter ? formatter(col, item.data[col]) : item.data[col];
                }
            }
        }
    }

    // Add support of columns sort
    table.tablesorter({sortList : defaultSorts});
}

$(document).ready(function() {

    // Customize table sorter default options
    $.extend( $.tablesorter.defaults, {
        theme: 'blue',
        cssInfoBlock: "tablesorter-no-sort",
        widthFixed: true,
        widgets: ['zebra']
    });

    var data = {"OkPercent": 0.0, "KoPercent": 100.0};
    var dataset = [
        {
            "label" : "FAIL",
            "data" : data.KoPercent,
            "color" : "#FF6347"
        },
        {
            "label" : "PASS",
            "data" : data.OkPercent,
            "color" : "#9ACD32"
        }];
    $.plot($("#flot-requests-summary"), dataset, {
        series : {
            pie : {
                show : true,
                radius : 1,
                label : {
                    show : true,
                    radius : 3 / 4,
                    formatter : function(label, series) {
                        return '<div style="font-size:8pt;text-align:center;padding:2px;color:white;">'
                            + label
                            + '<br/>'
                            + Math.round10(series.percent, -2)
                            + '%</div>';
                    },
                    background : {
                        opacity : 0.5,
                        color : '#000'
                    }
                }
            }
        },
        legend : {
            show : true
        }
    });

    // Creates APDEX table
    createTable($("#apdexTable"), {"supportsControllersDiscrimination": true, "overall": {"data": [0.0, 500, 1500, "[res_key=reportgenerator_summary_total]"], "isController": false}, "titles": ["[res_key=reportgenerator_summary_apdex_apdex]", "[res_key=reportgenerator_summary_apdex_satisfied]", "[res_key=reportgenerator_summary_apdex_tolerated]", "[res_key=reportgenerator_summary_apdex_samplers]"], "items": [{"data": [0.0, 500, 1500, "Sample Request"], "isController": false}, {"data": [0.0, 500, 1500, "Request to Get Repository Details"], "isController": false}, {"data": [0.0, 500, 1500, "Request to Update Repository Details"], "isController": false}]}, function(index, item){
        switch(index){
            case 0:
                item = item.toFixed(3);
                break;
            case 1:
            case 2:
                item = formatDuration(item);
                break;
        }
        return item;
    }, [[0, 0]], 3);

    // Create statistics table
    createTable($("#statisticsTable"), {"supportsControllersDiscrimination": true, "overall": {"data": ["[res_key=reportgenerator_summary_total]", 600, 600, 100.0, 269.75833333333316, 221, 911, 246.0, 341.9, 390.6999999999996, 551.8600000000001, 28.253908457336596, 31.66534716990017, 8.737374034658128], "isController": false}, "titles": ["[res_key=reportgenerator_summary_statistics_label]", "[res_key=reportgenerator_summary_statistics_count]", "[res_key=reportgenerator_summary_statistics_error_count]", "[res_key=reportgenerator_summary_statistics_error_percent]", "[res_key=reportgenerator_summary_statistics_mean]", "[res_key=reportgenerator_summary_statistics_min]", "[res_key=reportgenerator_summary_statistics_max]", "[res_key=reportgenerator_summary_statistics_median]", "[res_key=reportgenerator_summary_statistics_percentile_fmt]", "[res_key=reportgenerator_summary_statistics_percentile_fmt]", "[res_key=reportgenerator_summary_statistics_percentile_fmt]", "[res_key=reportgenerator_summary_statistics_throughput]", "[res_key=reportgenerator_summary_statistics_kbytes]", "[res_key=reportgenerator_summary_statistics_sent_kbytes]"], "items": [{"data": ["Sample Request", 200, 200, 100.0, 253.4600000000001, 222, 468, 238.0, 300.9, 345.74999999999994, 466.96000000000004, 9.956688405436351, 11.158880743764625, 2.79059528550804], "isController": false}, {"data": ["Request to Get Repository Details", 200, 200, 100.0, 303.13499999999993, 221, 911, 276.0, 396.70000000000005, 499.69999999999993, 893.0100000000018, 9.626492106276473, 10.788815821139776, 2.6980500336927222], "isController": false}, {"data": ["Request to Update Repository Details", 200, 200, 100.0, 252.6800000000001, 222, 503, 242.0, 295.4000000000001, 328.95, 463.0900000000008, 9.955697147692767, 11.157769799392703, 3.6556075464184383], "isController": false}]}, function(index, item){
        switch(index){
            // Errors pct
            case 3:
                item = item.toFixed(2) + '%';
                break;
            // Mean
            case 4:
            // Mean
            case 7:
            // Median
            case 8:
            // Percentile 1
            case 9:
            // Percentile 2
            case 10:
            // Percentile 3
            case 11:
            // Throughput
            case 12:
            // Kbytes/s
            case 13:
            // Sent Kbytes/s
                item = item.toFixed(2);
                break;
        }
        return item;
    }, [[0, 0]], 0, summaryTableHeader);

    // Create error table
    createTable($("#errorsTable"), {"supportsControllersDiscrimination": false, "titles": ["[res_key=reportgenerator_summary_errors_type]", "[res_key=reportgenerator_summary_errors_count]", "[res_key=reportgenerator_summary_errors_rate_error]", "[res_key=reportgenerator_summary_errors_rate_all]"], "items": [{"data": ["401/Unauthorized", 600, 100.0, 100.0], "isController": false}]}, function(index, item){
        switch(index){
            case 2:
            case 3:
                item = item.toFixed(2) + '%';
                break;
        }
        return item;
    }, [[1, 1]]);

        // Create top5 errors by sampler
    createTable($("#top5ErrorsBySamplerTable"), {"supportsControllersDiscrimination": false, "overall": {"data": ["[res_key=reportgenerator_top5_total]", 600, 600, "401/Unauthorized", 600, "", "", "", "", "", "", "", ""], "isController": false}, "titles": ["[res_key=reportgenerator_top5_label]", "[res_key=reportgenerator_top5_sample_count]", "[res_key=reportgenerator_top5_error_count]", "[res_key=reportgenerator_top5_error_label]", "[res_key=reportgenerator_top5_error_count]", "[res_key=reportgenerator_top5_error_label]", "[res_key=reportgenerator_top5_error_count]", "[res_key=reportgenerator_top5_error_label]", "[res_key=reportgenerator_top5_error_count]", "[res_key=reportgenerator_top5_error_label]", "[res_key=reportgenerator_top5_error_count]", "[res_key=reportgenerator_top5_error_label]", "[res_key=reportgenerator_top5_error_count]"], "items": [{"data": ["Sample Request", 200, 200, "401/Unauthorized", 200, "", "", "", "", "", "", "", ""], "isController": false}, {"data": ["Request to Get Repository Details", 200, 200, "401/Unauthorized", 200, "", "", "", "", "", "", "", ""], "isController": false}, {"data": ["Request to Update Repository Details", 200, 200, "401/Unauthorized", 200, "", "", "", "", "", "", "", ""], "isController": false}]}, function(index, item){
        return item;
    }, [[0, 0]], 0);

});
