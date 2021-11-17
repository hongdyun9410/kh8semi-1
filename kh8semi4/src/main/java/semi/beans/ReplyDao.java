package semi.beans;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class ReplyDao {

		public List<ReplyDto> ProductReplyMember(String memberId) throws Exception{
			Connection con = JdbcUtils.connect();
			String sql = "select buy.*,reply.* from reply left outer join buy on reply.product_no = buy.no where reply.member_id = ?";
			PreparedStatement ps = con.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			
			List<ReplyDto>list = new ArrayList<>();
			while(rs.next()) {
				ReplyDto replyDto = new ReplyDto();
				
				replyDto.setNo(rs.getInt("no"));
				replyDto.setMemberId(rs.getString("member_id"));
				replyDto.setProductNo(rs.getInt("product_no"));
				replyDto.setStarPoint(rs.getInt("starpoint"));
				replyDto.setContent(rs.getString("content"));
				replyDto.setTime(rs.getDate("time"));
				
				list.add(replyDto);
			}
			con.close();
			return list;
		}
		public List<ReplyDto> ProductReplyMember2(String memberId) throws Exception{
			Connection con = JdbcUtils.connect();
			String sql = "select * from reply where member_id = ?";
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setString(1, memberId);
			ResultSet rs = ps.executeQuery();
			
			List<ReplyDto>list = new ArrayList<>();
			while(rs.next()) {
				ReplyDto replyDto = new ReplyDto();
				
				replyDto.setNo(rs.getInt("no"));
				replyDto.setMemberId(rs.getString("member_id"));
				replyDto.setProductNo(rs.getInt("product_no"));
				replyDto.setStarPoint(rs.getInt("starpoint"));
				replyDto.setContent(rs.getString("content"));
				replyDto.setTime(rs.getDate("time"));
				
				list.add(replyDto);
			}
			con.close();
			return list;
		}
		public List<ProductDto> CanWriteReply(String memberId) throws Exception {
			Connection con = JdbcUtils.connect();
			String sql = "select product.* from product left outer join buy on product.no = buy.product_no where buy.member_id = ?";
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setString(1, memberId);
			ResultSet rs = ps.executeQuery();
			
			List<ProductDto>list  = new ArrayList<>();
			while(rs.next()) {
				ProductDto productDto = new ProductDto();
				
				productDto.setNo(rs.getInt("no"));
				productDto.setSmallTypeNo(rs.getInt("small_type_no"));
				productDto.setName(rs.getString("name"));
				productDto.setPrice(rs.getInt("price"));
				productDto.setDescription(rs.getString("description"));
				productDto.setViews(rs.getInt("views"));
				
				list.add(productDto );
			}
				con.close();
				return list;
			}
		

	
	public boolean insert(ReplyDto replyDto) throws Exception {
		Connection con = JdbcUtils.connect();
		
		String sql = "insert into reply values(reply_seq.nextval, ?, ?, ?, ?, default)";
		PreparedStatement ps = con.prepareStatement(sql);
		ps.setString(1, replyDto.getMemberId());
		ps.setInt(2, replyDto.getProductNo());
		ps.setInt(3, replyDto.getStarPoint());
		ps.setString(4, replyDto.getContent());
		int result = ps.executeUpdate();
		
		con.close();
		
		return result > 0;
	}
	
	public List<ReplyDto> list() throws Exception{
		Connection con = JdbcUtils.connect();
		
		String sql = "select * from reply order by time desc";
		PreparedStatement ps = con.prepareStatement(sql);
		ResultSet rs = ps.executeQuery();
		
		List<ReplyDto> replyList = new ArrayList<>();
		
		while(rs.next()) {
			ReplyDto replyDto = new ReplyDto();
			replyDto.setNo(rs.getInt("no"));
			replyDto.setMemberId(rs.getString("member_id"));
			replyDto.setProductNo(rs.getInt("product_no"));
			replyDto.setStarPoint(rs.getInt("starpoint"));
			replyDto.setContent(rs.getString("content"));
			replyDto.setTime(rs.getDate("time"));
			
			replyList.add(replyDto);
		}
		
		con.close();
		
		return replyList;
	}
	
	public List<ReplyDto> listByRownum(int begin,int end)throws Exception{
		Connection con= JdbcUtils.connect();
		
		String sql="select * from("
				+ "select rownum rn ,TMP.*from( "
				+ "select * from reply order by time desc "
				+ ")TMP "
				+ ")where rn between ? and ? ";
		
		PreparedStatement ps =con.prepareStatement(sql);
		ps.setInt(1, begin);
		ps.setInt(2, end);
		ResultSet rs = ps.executeQuery();
		

		List<ReplyDto> list = new ArrayList<>();
		while(rs.next()) {
			ReplyDto replyDto = new ReplyDto();
			replyDto.setNo(rs.getInt("no"));
			replyDto.setMemberId(rs.getString("member_id"));
			replyDto.setProductNo(rs.getInt("product_no"));
			replyDto.setContent(rs.getString("content"));
			replyDto.setTime(rs.getDate("time"));
			
			list.add(replyDto);
		}
		con.close();
		
		return list;
	
	}
}