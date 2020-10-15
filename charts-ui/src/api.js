 import { get } from './lib/client';

 function getCharts() {
     return get('/charts');
 }

 function getChart(chart) {
    return get(`/chart/${chart}`);
}

export default {getCharts, getChart}