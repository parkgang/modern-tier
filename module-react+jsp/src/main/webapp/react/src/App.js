import { Component } from "react";
import { Header } from "./components";
import { PostContainer } from "./containers";

export default class App extends Component {
  render() {
    return (
      <div>
        <Header />
        <PostContainer />
      </div>
    );
  }
}
