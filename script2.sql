USE [QLNS]
GO
/****** Object:  Table [dbo].[booktbl]    Script Date: 5/14/2021 11:25:13 AM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[booktbl](
	[IDSach] [int] NULL,
	[tenSach] [nvarchar](50) NULL,
	[tacGia] [nvarchar](50) NULL,
	[theLoai] [nvarchar](50) NULL,
	[NXB] [nvarchar](50) NULL,
	[giaTien] [int] NULL,
	[soLuong] [int] NULL
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[khachhangtbl]    Script Date: 5/14/2021 11:25:13 AM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[khachhangtbl](
	[IDKH] [int] NULL,
	[tenKH] [nvarchar](50) NULL,
	[diaChi] [nvarchar](50) NULL,
	[SDT] [int] NULL
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[nhanvientbl]    Script Date: 5/14/2021 11:25:13 AM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[nhanvientbl](
	[maNV] [int] NOT NULL,
	[tenNV] [nvarchar](50) NULL,
	[ngaySinh] [nvarchar](50) NULL,
	[gioiTinh] [nvarchar](50) NULL,
	[diaChi] [nvarchar](50) NULL,
	[SDT] [int] NULL,
 CONSTRAINT [PK_nhanvientbl] PRIMARY KEY CLUSTERED 
(
	[maNV] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[usertbl]    Script Date: 5/14/2021 11:25:13 AM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[usertbl](
	[maNV] [int] NOT NULL,
	[tenDangNhap] [nvarchar](50) NULL,
	[password] [nvarchar](50) NULL,
	[quyen] [nvarchar](50) NULL,
 CONSTRAINT [PK_usertble] PRIMARY KEY CLUSTERED 
(
	[maNV] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]
GO
INSERT [dbo].[booktbl] ([IDSach], [tenSach], [tacGia], [theLoai], [NXB], [giaTien], [soLuong]) VALUES (1, N'Doraemon', N'Fujio', N'Thiếu Nhi', N'Kim Đồng', 30000, 30)
INSERT [dbo].[booktbl] ([IDSach], [tenSach], [tacGia], [theLoai], [NXB], [giaTien], [soLuong]) VALUES (2, N'Nhà Giả Kim', N'Paulo Coelho', N'Khoa Học', N'Nhã Nam', 53000, 30)
INSERT [dbo].[booktbl] ([IDSach], [tenSach], [tacGia], [theLoai], [NXB], [giaTien], [soLuong]) VALUES (3, N'Kẻ Trộm Sách', N'Markus Zusak ', N'Văn Học', N'Hội Nhà Văn', 180000, 30)
INSERT [dbo].[booktbl] ([IDSach], [tenSach], [tacGia], [theLoai], [NXB], [giaTien], [soLuong]) VALUES (4, N'Truyện Kiều', N'Nguyễn Du', N'Văn Học', N'Văn Học', 30000, 30)
INSERT [dbo].[booktbl] ([IDSach], [tenSach], [tacGia], [theLoai], [NXB], [giaTien], [soLuong]) VALUES (5, N'Đắc Nhân Tâm', N'Dale Carnegie', N'Văn Học', N'Trí Việt', 50000, 40)
INSERT [dbo].[booktbl] ([IDSach], [tenSach], [tacGia], [theLoai], [NXB], [giaTien], [soLuong]) VALUES (6, N'Lược Sử Thế Giới', N'E.H Gombrich', N'Lịch Sử', N'Hồng Đức', 200000, 30)
INSERT [dbo].[booktbl] ([IDSach], [tenSach], [tacGia], [theLoai], [NXB], [giaTien], [soLuong]) VALUES (7, N'Sụp Đổ ', N'Jared Diamond', N'Lịch Sử', N'Mega', 340000, 20)
INSERT [dbo].[booktbl] ([IDSach], [tenSach], [tacGia], [theLoai], [NXB], [giaTien], [soLuong]) VALUES (8, N'Lịch Sử Thượng Đế', N'Karen Armstrong', N'Lịch Sử', N'Mega', 180000, 30)
INSERT [dbo].[booktbl] ([IDSach], [tenSach], [tacGia], [theLoai], [NXB], [giaTien], [soLuong]) VALUES (9, N'Quản Trị Marketing', N'Kotler Keller', N'Kinh Tế', N'Hồng Đức', 590000, 20)
INSERT [dbo].[booktbl] ([IDSach], [tenSach], [tacGia], [theLoai], [NXB], [giaTien], [soLuong]) VALUES (10, N'Siêu Tập Trung', N'Chris Balley', N'Kinh Tế', N'Hồng Đức', 100000, 30)
INSERT [dbo].[booktbl] ([IDSach], [tenSach], [tacGia], [theLoai], [NXB], [giaTien], [soLuong]) VALUES (11, N'Hoàng Tử Bé', N'Antoine De Saint', N'Thiếu Nhi', N'Hội Nhà Văn', 50000, 35)
INSERT [dbo].[booktbl] ([IDSach], [tenSach], [tacGia], [theLoai], [NXB], [giaTien], [soLuong]) VALUES (12, N'Dế Mèn Phiêu Lưu Ký', N'Tô Hoài', N'Thiếu Nhi', N'Kim Đồng', 40000, 35)
INSERT [dbo].[booktbl] ([IDSach], [tenSach], [tacGia], [theLoai], [NXB], [giaTien], [soLuong]) VALUES (13, N'Bản Đồ', N'Quỳnh Chi', N'Thiếu Nhi', N'Nhà Lao Động', 270000, 25)
INSERT [dbo].[booktbl] ([IDSach], [tenSach], [tacGia], [theLoai], [NXB], [giaTien], [soLuong]) VALUES (14, N'Cẩm Nang Golf', N'Phạm Thành Trí', N'Thể Thao', N'Giáo Dục Việt Nam', 340000, 25)
INSERT [dbo].[booktbl] ([IDSach], [tenSach], [tacGia], [theLoai], [NXB], [giaTien], [soLuong]) VALUES (15, N'Yoga Nâng Cao', N'Phan Thị Nga', N'Thể Thao', N'Dân Trí', 200000, 25)
INSERT [dbo].[booktbl] ([IDSach], [tenSach], [tacGia], [theLoai], [NXB], [giaTien], [soLuong]) VALUES (16, N'Yoga Toàn Thư', N'Swami ', N'Thể Thao', N'Đà Nẵng', 150000, 30)
INSERT [dbo].[booktbl] ([IDSach], [tenSach], [tacGia], [theLoai], [NXB], [giaTien], [soLuong]) VALUES (17, N'Thực Chiến Cờ Vua', N'Rebi', N'Thể Thao', N'Phương Đông', 30000, 30)
INSERT [dbo].[booktbl] ([IDSach], [tenSach], [tacGia], [theLoai], [NXB], [giaTien], [soLuong]) VALUES (18, N'Vincent VAN GOGH', N'Gérard Denizeau', N'Nghệ Thuật', N'Mega', 230000, 25)
INSERT [dbo].[booktbl] ([IDSach], [tenSach], [tacGia], [theLoai], [NXB], [giaTien], [soLuong]) VALUES (19, N'Hokusai', N'Johann Protais', N'Nghệ Thuật', N'Omega Plus', 254000, 25)
INSERT [dbo].[booktbl] ([IDSach], [tenSach], [tacGia], [theLoai], [NXB], [giaTien], [soLuong]) VALUES (20, N'Ăn Dặm Kiểu Nhật', N'Nguyễn Thị Hoa', N'Nấu Ăn', N'Thái Hà', 95000, 30)
INSERT [dbo].[booktbl] ([IDSach], [tenSach], [tacGia], [theLoai], [NXB], [giaTien], [soLuong]) VALUES (21, N'90 Món Ăn Mỗi Ngày', N'Quỳnh Chi', N'Nấu Ăn', N'Phụ Nữ', 35000, 30)
GO
INSERT [dbo].[khachhangtbl] ([IDKH], [tenKH], [diaChi], [SDT]) VALUES (1, N'Nguyen Van A', N'HCM', 9318293)
INSERT [dbo].[khachhangtbl] ([IDKH], [tenKH], [diaChi], [SDT]) VALUES (2, N'Nguyễn Văn C', N'CT', 18239211)
INSERT [dbo].[khachhangtbl] ([IDKH], [tenKH], [diaChi], [SDT]) VALUES (3, N'Nguyen Van B ', N'CM', 901723123)
GO
INSERT [dbo].[nhanvientbl] ([maNV], [tenNV], [ngaySinh], [gioiTinh], [diaChi], [SDT]) VALUES (1, N'Nguyen Minh', N'22/12', N'Nam', N'AG', 81239)
INSERT [dbo].[nhanvientbl] ([maNV], [tenNV], [ngaySinh], [gioiTinh], [diaChi], [SDT]) VALUES (2, N'Nguyen Hau', N'22/11', N'Nam', N'HCM', 2131231)
INSERT [dbo].[nhanvientbl] ([maNV], [tenNV], [ngaySinh], [gioiTinh], [diaChi], [SDT]) VALUES (3, N'Viet Hoang', N'23/1', N'Nam', N'KG', 9618723)
GO
INSERT [dbo].[usertbl] ([maNV], [tenDangNhap], [password], [quyen]) VALUES (0, N'admin', N'123456', N'Admin')
INSERT [dbo].[usertbl] ([maNV], [tenDangNhap], [password], [quyen]) VALUES (1, N'user1', N'12345', N'Nhân Viên')
INSERT [dbo].[usertbl] ([maNV], [tenDangNhap], [password], [quyen]) VALUES (2, N'user2', N'1234', N'Nhân Viên')
INSERT [dbo].[usertbl] ([maNV], [tenDangNhap], [password], [quyen]) VALUES (3, N'user3', N'123', N'Nhân Viên')
INSERT [dbo].[usertbl] ([maNV], [tenDangNhap], [password], [quyen]) VALUES (4, N'user4', N'123123', N'Nhân Viên')
GO
