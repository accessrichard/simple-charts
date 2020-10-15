import React from "react";
import {
  BrowserRouter as Router,
  Switch,
  Route,
  Link
} from "react-router-dom";
import Charts from "./charts/Charts"
import Chart from "./charts/Chart"

export default function Routes() {
    return (
      <Router>
          <Switch>
            <Route exact path="/">
              <Charts />
            </Route>
            <Route path="/dashboard" component={Chart}>
            </Route>
            <Route path="/reports"  component={Charts}>
            </Route>
          </Switch>
      </Router>
    );
  }
  