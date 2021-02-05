import React, { useEffect, useState } from 'react';
import api from '../api';
import { Chart as GChart } from "react-google-charts";

export default function Chart(props) {

    const [data, setData] = useState({
        options: {
            type: ""
        },
        gchart: {
            cols: [],
            rows: []
        }
    });

    const [error, setError] = useState("");

    useEffect(() => {

        async function fetchData() {
            await getChart(props.name).catch(err => setError(err.toString()));
        }

        fetchData();
    }, [props.name]);

    async function getChart(chart) {
        setError("");
        const res = await api.getChart(chart);
        setData(res);
    }

    return (
        <>
            <span>{error}</span>
            <GChart
                options={data.options.options}
                chartType={data.options.type}
                rows={data.gchart.rows}
                columns={data.gchart.cols}
                width="100%"
                height="500px"

            /></>
    )
}
