import React from 'react';
import List from '@material-ui/core/List';
import ListItem from '@material-ui/core/ListItem';
import { useParams } from 'react-router-dom';
import Chart from './Chart';

export default function Charts() {

    const { chartList } = useParams();

    function getCharts() {
        return chartList.split(',');
    }

    return (
        <List>
            
            {getCharts().map((chart, index) => (
                <div key={index}>                    
                <ListItem key={index}>
                    <Chart name={chart}></Chart>
                </ListItem>
                </div>
            ))}
        </List>
    )
}