<template>
  <div class="container">
    <header class="jumbotron">
      <ul>
        <li v-for="article in posts">
          <h2 >{{article.username}}</h2>
          <h3 >{{article.text}}</h3>
        </li>
      </ul>
    </header>
  </div>
</template>

<script>
import UserService from '../services/user.service';

export default {
  name: 'User',
  data() {
    return {
      posts: this.posts
    };
  },
  mounted() {
    UserService.getUserPage().then(
      response => {
        this.content = response.data;
        this.news = response.data.news;
      },
      error => {
        this.content =
          (error.response && error.response.data && error.response.data.message) ||
          error.message ||
          error.toString();
      }
    );
  }
};
</script>
