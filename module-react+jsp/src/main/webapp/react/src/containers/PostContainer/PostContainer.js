import { Component } from "react";
import { PostWrapper, Navigator, Post, Warning } from "../../components";
import * as service from "../../services/post";

export default class PostContainer extends Component {
  constructor(props) {
    super();
    this.state = {
      postId: 1,
      post: {
        title: null,
        body: null,
      },
      comments: [],
      // 요청이 응답을 기다리고 있는지 여부를 알려줌
      fetching: false,
      warningVisibility: false,
    };
  }

  componentDidMount() {
    this.fetchPostInfo(1);
  }

  fetchPostInfo = async (postId) => {
    this.setState({
      // 요청 중
      fetching: true,
    });

    try {
      // 방법1
      // const post = await service.getPost(postId);
      // console.log(post);
      // const comments = await service.getComments(postId);
      // console.log(comments);

      // 방법2: 2개의 Promise를 기다립니다.
      const info = await Promise.all([
        service.getPost(postId),
        service.getComments(postId),
      ]);
      // console.log(info);

      const { title, body } = info[0].data;
      const comments = info[1].data;

      this.setState({
        postId,
        post: {
          title,
          body,
        },
        comments,
        // 요청 완료
        fetching: false,
      });
    } catch (e) {
      // -1 page 탐색시 발생함
      this.setState({ fetching: false });
      this.showWarning();
      console.error("에러가 발생함", e);
    }
  };

  handleNavigateClick = (type) => {
    const postId = this.state.postId;

    if (type === "NEXT") {
      this.fetchPostInfo(postId + 1);
    } else {
      this.fetchPostInfo(postId - 1);
    }
  };

  showWarning = () => {
    this.setState({
      warningVisibility: true,
    });

    setTimeout(() => {
      this.setState({ warningVisibility: false });
    }, 1500);
  };

  render() {
    // 비구조화 할당 문법을 사용하여 "this.state.post.title" 이렇게 해야되는거를 바로 "post.title" 로 할 수 있어 가독성을 향상시킨다.
    const { postId, fetching, post, comments, warningVisibility } = this.state;
    return (
      <PostWrapper>
        <Navigator
          postId={postId}
          disabled={fetching}
          onClick={this.handleNavigateClick}
        />
        <Post title={post.title} body={post.body} comments={comments} />
        <Warning visible={warningVisibility} message="마지막 페이지입니다." />
      </PostWrapper>
    );
  }
}
