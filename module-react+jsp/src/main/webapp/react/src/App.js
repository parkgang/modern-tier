import React, { Component } from 'react';
import { createStore } from 'redux';
import { Provider } from 'react-redux';
import { BrowserRouter, Route, Switch } from 'react-router-dom';

import reducers from './reducers';
import { withLogined } from './hocs';
import { RankingList, CompetitionList, NotFound } from './components';

import './App.css';

const store = createStore(
  reducers,
  window.__REDUX_DEVTOOLS_EXTENSION__ && window.__REDUX_DEVTOOLS_EXTENSION__()
);

export default class App extends Component {
  render() {
    return (
      <>
        <Provider store={store}>
          <BrowserRouter>
            <Switch>
              {/* tomcat에 react를 실행시키는 구조적 한계 때문에 실제 파일 경로인 아래의 경로만이 root경로 역할을 할 수 있습니다. (현재 아키텍처로 root 경로 변경 불가능) (/react/dist) */}
              <Route
                exact
                path="/react/dist"
                component={withLogined(RankingList)}
              />
              <Route
                path="/competition"
                component={withLogined(CompetitionList)}
              />
              <Route component={NotFound} />
            </Switch>
          </BrowserRouter>
        </Provider>
      </>
    );
  }
}
