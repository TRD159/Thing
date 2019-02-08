public class BSTNode<E extends Comparable>
{
	private E data;
	private BSTNode<E> left;
	private BSTNode<E> right;

	public BSTNode(E data)
	{
		this.data 		= data;
		left = right	= null;
	}

	public E getData()
	{ return data; }
	
	public void setData(E data)
	{ this.data = data; }
	
	public BSTNode<E> getLeft()
	{ return left; }
	
	public void setLeft(BSTNode<E> left)
	{ this.left = left; }
	
	public BSTNode<E> getRight()
	{ return right; }
	
	public void setRight(BSTNode<E> right)
	{ this.right = right; }
}  
