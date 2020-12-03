import "./Navigator.css";
import { Button } from "semantic-ui-react";

const Navigator = ({ onClick, postId, disabled }) => (
  <div className="Navigator">
    <Button
      color="teal"
      content="Pervious"
      icon="left arrow"
      labelPosition="left"
      onClick={() => onClick("PREV")}
      disabled={disabled}
    />
    <div className="Navigator-page-num">{postId}</div>
    <Button
      color="teal"
      content="Next"
      icon="right arrow"
      labelPosition="right"
      className="Navigator-right-button"
      onClick={() => onClick("NEXT")}
      disabled={disabled}
    />
  </div>
);

export default Navigator;
