import React, { Component, Fragment } from 'react';
import { applyMiddleware, createStore } from 'redux';
import { Provider } from 'react-redux';
import { BrowserRouter, Route, Switch } from 'react-router-dom';

import reducers from './reducers';
import { logger } from './middlewares';

import { DownContainer, UpContainer } from './containers';

const preloadedState = window.__PRELOADED_STATE__;
delete window.__PRELOADED_STATE__;
const store = createStore(reducers, preloadedState, applyMiddleware(logger));

export default class App extends Component {
  render() {
    return (
      <Fragment>
        <Provider store={store}>
          <BrowserRouter>
            <Switch>
              <Route exact path="/" component={DownContainer} />
              <Route exact path="/test" component={UpContainer} />
              <Route /> {/* 404 error rendering */}
            </Switch>
          </BrowserRouter>
        </Provider>
      </Fragment>
    );
  }
}
