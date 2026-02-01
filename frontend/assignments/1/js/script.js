const tweetInput = document.querySelector(".tweet-input");
const postButtons = document.querySelectorAll(".post-btn");
const postsContainer = document.querySelector(".posts");

/* Enable / Disable Post button */
if (tweetInput) {
  tweetInput.addEventListener("input", () => {
    const hasText = tweetInput.value.trim().length > 0;
    postButtons.forEach(btn =>
      btn.classList.toggle("disabled", !hasText)
    );
  });
}

/* Post new tweet */
postButtons.forEach(btn => {
  btn.addEventListener("click", e => {
    if (btn.classList.contains("disabled")) return;

    const text = tweetInput.value.trim();
    if (!text) return;

    const post = createPostElement(text, true);
    postsContainer.prepend(post);

    tweetInput.value = "";
    postButtons.forEach(b => b.classList.add("disabled"));
  });
});

/* Utility */
const randomCount = () => Math.floor(Math.random() * 100);

/* Create post */
function createPostElement(text, isNew = false) {
  const post = document.createElement("article");
  post.className = "post";

  const likeCount = isNew ? 0 : randomCount();
  const commentCount = isNew ? 0 : randomCount();
  const repostCount = isNew ? 0 : randomCount();

  post.innerHTML = `
  <div class="post-avatar">
    <img src="./assets/icons/profile-icon.svg" />
  </div>

  <div class="post-body">
    <div class="post-header">
      <span class="post-name">Pratiksha</span>
      <span class="post-handle">@prat_iksha2432 · now</span>
      <img class="post-more" src="./assets/icons/dot-icon.svg" />
    </div>

    <p class="post-text">${text}</p>

    <div class="post-actions">
      <div class="action comment">
        <img src="./assets/icons/comment.svg" />
        <span>0</span>
      </div>

      <div class="action repost">
        <img src="./assets/icons/retweet.svg" />
        <span>0</span>
      </div>

      <div class="action like">
        <img src="./assets/icons/like.svg" />
        <span>0</span>
      </div>

      <div class="action views">
        <img src="./assets/icons/stats.svg" />
        <span>0</span>
      </div>

      <div class="action share">
        <img src="./assets/icons/share.svg" />
      </div>

      <div class="action bookmark">
        <img src="./assets/icons/bookmark-icon.svg" />
      </div>
    </div>

    <div class="comments hidden">
      <input class="comment-input" placeholder="Add a comment" />
      <button class="comment-btn">Comment</button>
      <div class="comment-list"></div>
    </div>
  </div>
`;


  setupPostInteractions(post);
  return post;
}

/* Post interactions */
function setupPostInteractions(post) {
  /* Like */
  const like = post.querySelector(".like");
  const likeIcon = like.querySelector("img");
  const likeCount = like.querySelector("span");
  let liked = false;

  like.addEventListener("click", () => {
    liked = !liked;
    likeIcon.src = liked
      ? "./assets/icons/like-pink.svg"
      : "./assets/icons/like.svg";
    likeCount.textContent = Number(likeCount.textContent) + (liked ? 1 : -1);
  });

  /* Repost */
  const repost = post.querySelector(".repost");
  const repostCount = repost.querySelector("span");
  let reposted = false;

  repost.addEventListener("click", () => {
    reposted = !reposted;
    repostCount.textContent = Number(repostCount.textContent) + (reposted ? 1 : -1);
  });

  /* Comments */
  const commentIcon = post.querySelector(".comment");
  const commentsBox = post.querySelector(".comments");
  const commentInput = post.querySelector(".comment-input");
  const commentBtn = post.querySelector(".comment-btn");
  const commentList = post.querySelector(".comment-list");
  const commentCount = commentIcon.querySelector("span");

  commentIcon.addEventListener("click", () => {
    commentsBox.classList.toggle("hidden");
  });

  commentBtn.addEventListener("click", () => {
    const text = commentInput.value.trim();
    if (!text) return;

    const div = document.createElement("div");
    div.className = "comment-item";
    div.textContent = text;

    commentList.appendChild(div);
    commentInput.value = "";
    commentCount.textContent = Number(commentCount.textContent) + 1;
  });
}

/* Mobile drawer */
const avatarTrigger = document.querySelector(".mobile-avatar-btn");
const drawer = document.querySelector(".mobile-drawer");
const backdrop = document.querySelector(".drawer-backdrop");

if (avatarTrigger && drawer && backdrop) {
  avatarTrigger.addEventListener("click", () => {
    drawer.classList.add("open");
    backdrop.classList.add("show");
  });

  backdrop.addEventListener("click", () => {
    drawer.classList.remove("open");
    backdrop.classList.remove("show");
  });
}
/* Initialize default HTML posts */
document.querySelectorAll(".post").forEach(post => {
  if (post.dataset.initialized) return;

  post.querySelectorAll(".action span").forEach(span => {
    span.textContent = Math.floor(Math.random() * 100);
  });

  setupPostInteractions(post);
  post.dataset.initialized = "true";
});

