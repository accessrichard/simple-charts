import React, { useEffect, useState } from 'react';
import List from '@material-ui/core/List';
import ListItem from '@material-ui/core/ListItem';
import ListItemText from '@material-ui/core/ListItemText';
import ListItemSecondaryAction from '@material-ui/core/ListItemSecondaryAction';
import Switch from '@material-ui/core/Switch';
import api from '../api';
import {
    useHistory
} from "react-router-dom";
import { useParams } from 'react-router-dom';


export default function ChartList() {

    const [charts, setCharts] = useState([]);
    const [checked, setChecked] = useState([]);
    const { chartList } = useParams();
    const history = useHistory();

    const handleToggle = (value) => () => {
        const currentIndex = checked.indexOf(value);
        const newChecked = [...checked];

        if (currentIndex === -1) {
            newChecked.push(value);
        } else {
            newChecked.splice(currentIndex, 1);
        }

        history.push(`/report/${newChecked.join(',')}`)
        setChecked(newChecked);
    };


    useEffect(() => {
        if (chartList) {
            const selectedCharts = chartList.split(',');
            setChecked(selectedCharts);
        }

        api.getCharts().then(resp => {
            setCharts(resp);
        });
    }, []);
    return (
        <List>
            {charts.map((chart, index) => (
                <ListItem button key={index}> <ListItemText primary={chart} />
                    <ListItemSecondaryAction>
                        <Switch
                            edge="end"
                            onChange={handleToggle(chart)}
                            checked={checked.indexOf(chart) !== -1}
                            inputProps={{ 'aria-labelledby': `switch-list-label-${chart}` }}
                        />
                    </ListItemSecondaryAction>
                </ListItem>
            ))}
        </List>
    )
}