import React from "react";
import ReactDOM from "react-dom";
import Dashboard from './Dashboard';
import {
  BrowserRouter as Router,
  Switch,
  Route
} from "react-router-dom";

function App() {
  return (
    <React.Fragment>
      <Router>
        {/* A <Switch> looks through its children <Route>s and
            renders the first one that matches the current URL. */}
        <Switch>
          <Route path="/">
            <Dashboard />
          </Route>
        </Switch>
      </Router>
    </React.Fragment>
  );
}

ReactDOM.render(<App />,document.getElementById('root'));
