object LastElementInList {
  def main(args: Array[String]): Unit = {
    val list = List(1,2,3,4,5,6);
    if(list.size==0){
      println("Invalid");
    }
    else{
      println(list.last);
    }
  }
}
