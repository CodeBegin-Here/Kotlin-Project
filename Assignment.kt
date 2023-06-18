// User class representing a forum user
data class User(val id: Int, val username: String)

// Post class representing a user's post
data class Post(val id: Int, val author: User, val content: String) {
    val comments = mutableListOf<Comment>()
    val blockedUsers = mutableSetOf<User>()

    fun addComment(comment: Comment, user: User) {
        if (!blockedUsers.contains(user)) {
            comments.add(comment)
        }
    }

    fun blockUser(user: User) {
        blockedUsers.add(user)
    }
}

// Comment class representing a comment on a post
data class Comment(val id: Int, val author: User, val content: String)

// Relationship class representing the relationship between two users
data class Relationship(val user1: User, val user2: User, val type: String)

// Forum class representing the coding forum
class Forum {
    val users = mutableListOf<User>()
    val posts = mutableListOf<Post>()
    val relationships = mutableListOf<Relationship>()

    fun createUser(username: String): User {
        val user = User(users.size + 1, username)
        users.add(user)
        return user
    }

    fun createPost(author: User, content: String): Post {
        val post = Post(posts.size + 1, author, content)
        posts.add(post)
        return post
    }

    fun createComment(post: Post, author: User, content: String): Comment {
        val comment = Comment(post.comments.size + 1, author, content)
        post.addComment(comment, author)
        return comment
    }

    fun createRelationship(user1: User, user2: User, type: String) {
        val relationship = Relationship(user1, user2, type)
        relationships.add(relationship)
    }
}

// Example usage
fun main() {
    val forum = Forum()

    val user1 = forum.createUser("Alice")
    val user2 = forum.createUser("Bob")
    val user3 = forum.createUser("Charlie")

    val post = forum.createPost(user1, "Check out my new project!")

    val comment1 = forum.createComment(post, user2, "Looks great!")
    val comment2 = forum.createComment(post, user3, "Nice work!")

    post.blockUser(user2) // User2 is blocked from commenting on the post

    val comment3 = forum.createComment(post, user2, "I can't comment on this post anymore.") // This comment will be blocked

    println("Post comments:")
    for (comment in post.comments) {
        println("${comment.author.username}: ${comment.content}")
    }
}
