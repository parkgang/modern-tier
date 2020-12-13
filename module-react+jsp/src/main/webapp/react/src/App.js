import React, { Component, Fragment } from 'react';
import { applyMiddleware, createStore } from 'redux';
import { Provider } from 'react-redux';
import { BrowserRouter, Route, Switch } from 'react-router-dom';

import reducers from './reducers';
import { logger } from './middlewares';

import { withLogined } from './hocs';
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
              {/* tomcat에 react를 실행시키는 구조적 한계 때문에 실제 파일 경로인 아래의 경로만이 root경로 역할을 할 수 있습니다. (현재 아키텍처로 root 경로 변경 불가능) (/react/dist) */}
              <Route
                exact
                path="/react/dist"
                component={withLogined(DownContainer)}
              />
              <Route exact path="/test" component={UpContainer} />
              <Route /> {/* 404 error rendering */}
            </Switch>
          </BrowserRouter>
        </Provider>
      </Fragment>
    );
  }
}
