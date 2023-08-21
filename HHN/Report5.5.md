# Q1: Rack trong hdfs là gì?
- Rack là một tập hợp vật lý gồm các nút trong cụm Hadoop (có thể từ 30 đến 40).
- 1 cluster lớn có thể có nhiều rack
- Giao tiếp giữa các Node dữ liệu có trên cùng một rack nhanh hơn khá nhiều so với ở 2 giá khác nhau. 
=> Namenode chọn Datanode gần nhất để đạt được hiệu suất tối đa trong khi thực hiện đọc/ghi thông tin giúp giảm lưu lượng mạng
# Q2: Thứ tự của block replication trong hdfs
- Hadoop đặt replica đầu tiên trên cùng một node vs client (client đang chạy bên ngoài cụm => một nút sẽ được chọn ngẫu nhiên)
- Bản sao replica được đặt trên rack được chọn ngẫu nhiên khác với replica thứ nhất.
- Replica thứ ba được đặt trên một node được chọn ngẫu nhiên trên cùng một giá với replica thứ hai.
- Bất kỳ replica nào khác được đặt trên các node được chọn ngẫu nhiên mà không đặt quá nhiều replica trong cùng một rack.
# Q3: Trình tự đọc/ghi trong hdfs
## Ghi:
![image](https://github.com/namdeptrai1102/DE_internship/assets/109681639/cb6b4026-fe21-4568-a921-ef67669e1acb)
- Client ghi đệm data trên đĩa cục bộ, đợi cho đến khi có đủ dữ liệu để tạo thành 1 data-block HDFS trước khi liên hệ với Namenode để yêu cầu lưu trữ dữ liệu này.
- Sau khi liên hệ vs client, Namenode check các quyền cần thiết, trả về client dsach các DataNode để ghi vào
- Client bắt đầu ghi vào Datanode
- Datanode nhận dữ liệu theo từng phần, ghi vào local repository rồi chuyển phần đó sang Datanode tiếp theo trong dsach
- 1 data pipeline hình thành từ client đến tất cả các Datanodes, 1 Datanode có thể đồng thởi nhận và truyền data.
## Đọc:
![image](https://github.com/namdeptrai1102/DE_internship/assets/109681639/8d175c02-8f02-4d82-bc32-9d35d0812863)
- Client gọi RPC (lời gọi thủ tục từ xa) cho Namenode để nhận về vị trí các block chứa file mà client muốn đọc
- Namenode trả về list địa chỉ Datanode cần đọc, đc xếp theo độ gần vs client. Nếu client nằm trên node chứa data nó cần thì sẽ đọc locally.
- Với ng dùng, ứng dụng client đọc như 1 luồng liên tục.
- Ktra lỗi bằng check sum. Lỗi => tự động chuyển thằng datanode khác, sẽ k quay lại node này nữa, báo cho Namenode để xử lí.
- Lưu ý: Namenode chỉ có tác dụng xử lí request cung cấp data node location. Dữ liệu sẽ k dc truyền qua name node mà client đọc trực tiếp từ data node để tránh bottleneck.
## Độ ưu tiên đọc/ghi
- Ưu tiên theo độ gần với node client (cùng node, cùng rack, khác rack)
# Q4: Block report là gì?
- DataNode xác định các bản sao khối mà nó sở hữu cho NameNode bằng cách gửi block report.
  - Block report chứa ID block,  generation stamp(cái này để xdinh phiên bản) và độ dài cho mỗi replica block mà Server lưu trữ.
  - Block report đầu tiên được gửi ngay sau khi đăng ký DataNode (1 báo cáo khối đầy đủ chứa danh mục tất cả các khối của node)
  - Các block report tiếp theo được gửi mỗi giờ (đây là các báo cáo khối gia tăng, cung cấp cho NameNode cập nhật về vị trí của các bản sao khối trên cụm và thông tin liên quan đến các khối đã được thêm/xóa gần đây)
- Giống như block report, DataNode cũng gửi cache report đến NameNode.
  - Cache report chứa danh sách các khối được lưu trong cache trên DataNode.
  - Sau khi nhận được report này, NameNode xác định trạng thái cache trên DataNode và phản hồi bằng cách gửi các hướng dẫn về bộ nhớ đệm để DataNode thực thi.
- Nếu block & cache report xảy ra quá thường xuyên hoặc mất quá nhiều thời gian để hoàn thành, chúng có thể khiến cụm Hadoop gặp trục trặc.
  - Chúng cũng có thể khiến cụm không thể phục vụ các yêu cầu của người dùng cuối một cách hiệu quả.
  => Để tránh kết quả như vậy, admin nên quan sát chặt chẽ các hoạt động block & cache report trên mỗi DataNode, nhanh chóng xác định các DataNode nơi có quá nhiều hoạt động trong số này đang xảy ra hoặc mất quá nhiều thời gian để hoàn thành, suy ra nguyên nhân của sự cố và giải quyết tốt trước khi người dùng cuối nhận thấy bất kỳ độ trễ nào.
# Q5: Mqh giữa cải thiện tốc độ MapReduce và block size
- Nếu split-size và kích thước khối HDFS bằng với số lượng mapper được tạo ra trong một công việc MapReduce, thì số lượng input splits một tệp đầu vào bằng với số lượng khối HDFS trong tệp đó.
- Nếu kích thước khối quá nhỏ, nó sẽ dẫn đến quá nhiều mapper với ít hoạt động và tốn chi phí quản lý.
- Mặt khác, kích thước khối không nên quá lớn, dẫn đến quá ít tác vụ mapper và tăng thời gian thực hiện công việc.
- Block size lý tượng:
  - Kích thước khối càng lớn càng tốt chưa chắc đã là một chiến lược tốt vì hai lý do:
    - Số lượng mapper được sinh ra có thể quá ít, nếu  block size = split size => sử dụng không đủ năng lực của cụm.
    - Nếu các kết nối TCP liên tục bị hỏng, việc truyền lại toàn bộ đoạn dữ liệu sẽ tốn kém hơn khi kích thước đoạn dữ liệu lớn.
  - Trên thực tế, không có một kích thước khối HDFS nào phù hợp với tất cả các trường hợp sử dụng. Ta phải xem xét cụm, dữ liệu đầu vào và loại xử lý mong muốn. Kích thước khối HDFS có thể được thay đổi riêng lẻ từ tệp này sang tệp khác.
# Q6: Quá trình ghi khi client ghi đệm chưa đủ 1 block
Tùy thuộc vào cấu hình của hệ thống được cài đặt thế nào, có 1 số giải pháp sau
- Replication và Block Incomplete:
  - HDFS có thể cho phép một phần của block cuối cùng trong tệp dữ liệu để không đủ kích thước block tiêu chuẩn (block incomplete)
  - Nếu dữ liệu ghi không đủ để đạt đến kích thước block tiêu chuẩn, HDFS sẽ lưu trữ những gì đã được ghi và đánh dấu block này là "incomplete."
  - Block incomplete vẫn sẽ được sao chép và duy trì theo nguyên tắc của HDFS.
  - Khi người dùng ghi thêm dữ liệu đến block này và đủ kích thước, nó sẽ được hoàn chỉnh.
- Wait for Data:
  - Một ứng dụng có thể chờ đợi đến khi đủ dữ liệu được ghi hoặc hết timeout trước khi liên hệ với Namenode.
  - Có thể đặc biệt hữu ích trong một số tình huống, nhưng nó có thể làm tăng thời gian chờ cho ứng dụng.
- Sử dụng hệ thống đệm (Buffering):
  - Một phần của dữ liệu ghi có thể được đệm trong bộ nhớ hoặc trên đĩa cục bộ cho đến khi đủ dữ liệu để tạo thành một block hoàn chỉnh.
  - Sau đó, block đầy đủ được tạo ra và ghi vào HDFS.
- Sử dụng block nhỏ hơn:
  - Khi dữ liệu nhỏ hơn kích thước block, HDFS sẽ xử lý nó bằng cách lưu trữ dữ liệu trong một block riêng lẻ nhỏ hơn kích thước tiêu chuẩn (block này có thể có kích thước thực tế tương ứng với dữ liệu mà bạn đã ghi)
  - Sau đó, Namenode sẽ theo dõi các block này và quản lý các metadata liên quan (quá nhiều block nhỏ có thể dẫn đến 1 sô overhead về metadata trong HDFS)
  - Khi cần đọc dữ liệu, HDFS sẽ đảm bảo rằng các block nhỏ này được ghép lại đúng trình tự để đảm bảo tính toàn vẹn của dữ liệu ban đầu.
# Q7 khi nào việc ghi trong hdfs được coi là thành công?  
Trong Hadoop, có 2 parameters liên quan đến replication
- dfs.replication: Số lượng replica thực tế có thể được chỉ định khi tệp được tạo. Giá trị mặc định được sử dụng nếu bản sao không được chỉ định trong thời gian tạo. Quá trình sao chép tới dfs.replication sẽ diễn ra theo quy trình tuần tự.
- dfs.namenode.replication.min : replicas block tối thiểu.
=> Khi dfs.namenode.replication.min đã được đáp ứng, thao tác ghi sẽ được coi là thành công.
# Q8: DataNode có gửi heartbeat và block report tất cả các active và Stanby node không
CÓ
- Để các standby NameNode giữ trạng thái của chúng được đồng bộ hóa với active NameNode, việc triển khai hiện tại yêu cầu các nút có quyền truy cập vào một thư mục trên thiết bị lưu trữ dùng chung (ví dụ: rack NFS từ NAS, JournalNode).
- Khi bất kỳ sửa đổi không gian tên nào được thực hiện bởi activve NameNode, nó sẽ ghi nhật ký sửa đổi một cách lâu dài vào 1 edit log được lưu trữ trong thư mục dùng chung.
- Các standby NameNode liên tục theo dõi thư mục này để chỉnh sửa và khi nó thấy các chỉnh sửa, nó sẽ cập nhật chúng vào không gian tên của chính nó.
- Trong trường hợp chuyển đổi dự phòng, standby NameNode sẽ đảm bảo rằng nó đã đọc tất cả các chỉnh sửa từ bộ nhớ dùng chung trước khi tự thăng cấp lên active Node (đảm bảo rằng trạng thái không gian tên được đồng bộ hóa hoàn toàn trước khi chuyển đổi dự phòng xảy ra).
- Để cung cấp chuyển đổi dự phòng nhanh, các standby NameNode cũng cần có thông tin cập nhật về vị trí của các khối trong cụm.
=> Để đạt được điều này, các DataNode được cấu hình với vị trí của tất cả các NameNode và gửi thông tin vị trí khối cũng như heartbeat tới tất cả các NameNode.
# Q9: Suffling
Shuffling là quá trình chuyển giao data từ mapper sang reducer, nó có thể bắt đầu chạy trước khi mapper hoàn thành tất cả tác vụ. Quá trình này bao gồm:
- Sorting: Các cặp key-val từ các Mapper được sắp xếp dựa trên key => đảm bảo rằng các giá trị có cùng key sẽ được nhóm lại cùng nhau để xử lý bởi cùng một Reducer.
- Partitioning: Sau khi sắp xếp, dữ liệu sẽ được chia thành các partitions tương ứng với số lượng Reducer. Mỗi phần sẽ chứa các cặp key-val được gửi đến một Reducer cụ thể.
- Data Transfer: Dữ liệu được chuyển giao từ Mapper đến Reducer tương ứng (thường bao gồm việc truyền dữ liệu qua mạng từ các nút của cluster đến nút chứa Reducer)
- Ghi dữ liệu vào Reducer Input: Sau khi dữ liệu đã được sắp xếp và chuyển giao, nó được lưu trữ trong input của các Reducer để tiếp tục xử lý bởi các Reducer.
# Q10: Nhiều key vào cùng 1 phân vùng => out phân vùng thì sao
- Nếu số lượng key-value trong một phân vùng vượt quá kích thước tối đa cho phân vùng đó, một số vấn đề có thể xảy ra:
  - Bị tràn bộ nhớ: Nếu một phân vùng có quá nhiều key-value, nó có thể dẫn đến tràn bộ nhớ trên máy tính chứa Reducer => gây ra lỗi và làm giảm hiệu suất
  - Thời gian Shuffling tăng lên: Quá nhiều dữ liệu trong một phân vùng có thể làm tăng thời gian cần thiết để truyền dữ liệu từ Mapper đến Reducer thông qua mạng => trễ trong quá trình Shuffling.
  - Không cân bằng Reducer: Nếu một phân container quá nhiều dữ liệu, nó có thể gây ra sự không cân bằng trong việc xử lý của các Reducer. Một số Reducer sẽ phải xử lý nhiều dữ liệu hơn, trong khi một số khác ít hơn.
- Giải pháp:
  - Tăng kích thước của phân vùng: tăng kích thước tối đa cho mỗi phân vùng trong cấu hình MapReduce để chứa nhiều dữ liệu hơn.
  - Sử dụng Combiner: Combiner là một phần của MapReduce cho phép tổng hợp dữ liệu trung gian trước khi nó được chuyển đến Reducer.
  - Sử dụng Compresssion: nén dữ liệu để giảm kích thước dữ liệu trung gian và giảm tải trên mạng trong quá trình Shuffling.
# Q11: 2 cơ chế khởi động lại RM: bảo vệ, ko bảo vệ
- Non-work-preserving RM restart (Khởi động lại RM không bảo toàn công việc)
  - RM sẽ lưu metadata ứng dụng (tức là ApplicationSubmissionContext) trong pluggable state-store khi ứng dụng khách gửi ứng dụng và cũng lưu trạng thái cuối cùng của ứng dụng, chẳng hạn như trạng thái hoàn thành (thất bại, bị hủy hoặc đã hoàn thành) và chẩn đoán khi ứng dụng hoàn tất,các thông tin xác thực như khóa bảo mật, token để hoạt động trong môi trường an toàn.
  - Khi RM tắt, miễn là thông tin bắt buộc (metadata ứng dụng và thông tin đăng nhập bên cạnh nếu chạy trong môi trường an toàn) có sẵn trong state-store, thì khi RM khởi động lại, nó có thể lấy metadata ứng dụng từ state-store và gửi lại ứng dụng.
  - RM sẽ không gửi lại các ứng dụng nếu chúng đã được hoàn thành (tức là không thành công, bị hủy hoặc hoàn thành) trước khi RM ngừng hoạt động.
  - NodeManagers và khách hàng trong thời gian ngừng hoạt động của RM sẽ tiếp tục thăm dò RM cho đến khi RM xuất hiện. Khi RM xuất hiện, nó sẽ gửi lệnh đồng bộ hóa lại tới tất cả các NodeManager và ApplicationMaster mà nó đang giao tiếp qua heartbeat.
  - Các NodeManager sẽ hủy tất cả các container được quản lý của nó và đăng ký lại với RM.
  - Sau khi RM khởi động lại và tải tất cả siêu dữ liệu ứng dụng, thông tin xác thực từ state-store và đưa chúng vào bộ nhớ, nó sẽ tạo ApplicationMastermới cho từng ứng dụng chưa hoàn thành và khởi động lại ứng dụng đó như bình thường.
  **Công việc của các ứng dụng đang chạy trước đó bị mất theo cách này do về cơ bản chúng bị RM kill thông qua lệnh đồng bộ lại khi khởi động lại.**
- Work-preserving RM restart (Khởi động lại RM bảo toàn công việc)
  - RM đảm bảo tính bền vững của trạng thái ứng dụng và tải lại trạng thái đó khi khôi phục, tập trung vào việc xây dựng lại toàn bộ trạng thái đang chạy của cụm YARN, phần lớn trong số đó là trạng thái của scheduler trung tâm bên trong RM (nơi theo dõi tất cả container’s life-cycle, các yêu cầu tài nguyên, hàng đợi, v.v. => RM không cần tắt AM và chạy lại ứng dụng từ đầu mà ứng dụng có thể đơn giản đồng bộ lại với RM và tiếp tục từ nơi nó đã dừng lại.
  - RM phục hồi trạng thái đang chạy của nó bằng cách tận dụng trạng thái container được gửi từ tất cả các NM.
  - NM sẽ không hủy các container khi nó đồng bộ hóa lại với RM đã khởi động lại mà tiếp tục quản lý các container và gửi trạng thái container tới RM khi nó đăng ký lại.
  - RM tái tạo lại các phiên bản container và trạng thái lập lịch trình của các ứng dụng được liên kết bằng cách sử dụng thông tin của các container này.
  - Trong thời gian chờ đợi, AM cần gửi lại các yêu cầu tài nguyên chưa xử lý cho RM vì RM có thể mất các yêu cầu chưa được thực hiện khi tắt máy (Lập trình viên sử dụng thư viện AMRMClient để giao tiếp với RM không cần phải lo lắng về phần AM gửi lại yêu cầu tài nguyên cho RM khi đồng bộ hóa lại, vì nó được thư viện tự động đảm nhận).
# Q12: Active RM đồng bộ standby RM ntn
*Em đã trả lời câu này trong Q8*
# Q13: Ưu nhược điểm của execution cluster & client mode:
- Client mode:
  - Driver chạy trên một Master node bên trong một quy trình chuyên dụng (nghĩa là nó có tất cả các tài nguyên sẵn có để thực hiện công việc)
  - Driver mở 1 Netty HTTP Server chuyên dụng và phân phối các tệp JAR được chỉ định cho tất cả các nút Worker **(lợi thế lớn)**.
  - Vì Master Node có các tài nguyên chuyên dụng của riêng nó nên không cần phải "tiêu tốn" tài nguyên Worker cho chương trình Driver.
  - Nếu Driver process chết => cần một hệ thống giám sát bên ngoài để thiết lập lại quá trình thực thi của nó.
- Cluster mode:
  - Driver chạy trên một trong các nút Worker của cụm. Worker được chọn bởi Master leader
  - Driver chạy như một quy trình chuyên dụng, độc lập bên trong Worker.
  - Các chương trình Driver chiếm ít nhất 1 lõi và một lượng bộ nhớ chuyên dụng từ một trong các Worker **(điều này có thể được định cấu hình)**.
  - Driver process có thể được theo dõi từ Master Node bằng cách sử dụng --supervise flag và được đặt lại trong trường hợp nó chết.
  - Khi làm việc ở cluster mode, tất cả các JAR liên quan đến việc thực thi ứng dụng cần phải được cung cấp công khai cho tất cả Worker (có nghĩa là có thể đặt chúng theo cách thủ công ở một nơi dùng chung hoặc trong một thư mục cho từng Worker).
- Từ so sánh trên ta rút ra đc ưu nhược điểm của 2 mode:
  - Client mode có tài nguyên Driver tập trung trên server riêng biệt và quản lý tệp JAR dễ dàng nhưng nếu  driver process có vde thì lại yêu cầu quản lý bổ sung từ Master Node bên ngoài.
  - Cluster Mode có khả năng chia sẻ tài nguyên và theo dõi cũng như khôi phục tự động nhưng nó cần sử dụng tài nguyên từ worker và phân phối JARs thủ công.
# Q14: Phép tính chỉ có thể thực hiện = dataset
# Q14: Transformation: narrow, wide (suffles-trao đổi giữa các partition)
- RDD bất biến nhưng có thể tạo mới bằng transformation cái hiện tại:
  - Narrow transformation (1-1): 1 input partition chỉ cho ra 1 output partition (k yêu cầu shuffled)
  - Wide transformation (1-nhiều): những input partitions đóng góp ra 1 số output partition (shuffled chính là vs Spark trao đổi các phân vùng trên cluster)
- Shuffle map task:
  - Spark trao đổi để phân vùng lại, ghi output ra đĩa
  - Chạy trong mọi state trừ final state
  - Có thể dùng lại các shuffle từ cviec trc đó thay vì tính toán lại
  - Shuffle persistence: lưu trữ tạm thời các dữ liệu sau khi quá trình shuffle đã hoàn thành, để có thể tối ưu hóa lại việc sử dụng chúng trong các công việc sau này và giảm tối đa việc di chuyển dữ liệu qua mạng.

