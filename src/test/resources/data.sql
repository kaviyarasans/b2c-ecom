insert into producers(id, name, created_at) values
('1bf3cf89-f87c-46fc-8605-028ea638b138', 'NIKE', now()),
('cde77934-d697-4610-a2ee-64387c144f55', 'ADIDAS', now()),
('54c7071f-13ed-4ff9-aa2e-2b56f5176e51', 'REEBOK', now()),
('0744cffa-e1b1-4096-b1dc-db7add47a902', 'ASICS', now()),
('b8b71686-2846-4cf0-9008-9d45b136c723', 'SKETCHERS', now());


insert into marketplaces(id, description) values
('amazon.ae', 'Amazon US'),
('flipkart.com', 'Flipkart'),
('croma.in', 'Tata Croma Store');


insert into seller_infos(id, country, external_id, marketplace_id, name, url) values
('c9786789-d94a-4b20-bd3c-85c410cba2e5', 'USA', 'amazon.ae.gem', 'amazon.ae', 'Gem Clothings', 'gemstores.com'),
('4d0f29d3-d773-45e6-b101-3a1cb6aab1a7', 'India', 'flipkart.com.gem', 'flipkart.com', 'Gem Clothings India', 'gemstores.com'),
('3248e3b3-c002-4bd4-9ae2-29d74d55f447', 'USA', 'amazon.ae.sting', 'amazon.ae', 'Sting Clothings', 'stingstores.com'),
('239f90d5-e75e-41e8-a3c5-13c06675a694', 'USA', 'amazon.ae.luke', 'amazon.ae', 'Luke Footwears', 'luke.com'),
('f82c54c5-c792-4461-af60-75381fea3361', 'USA', 'amazon.ae.happilo', 'amazon.ae', 'Happilo', 'happilo.com'),
('ffffb520-5626-49ec-8d32-297b598cc7e0', 'India', 'flipkart.com.urban', 'flipkart.com', 'Urban Clothings', 'urbanstores.com'),
('8be34f2d-f8f6-4b48-9f4d-52656bfc8180', 'India', 'flipkart.com.samsung', 'flipkart.com', 'Samsung Electronics', 'samsung.com'),
('b0bb27a6-79b8-4682-9495-32afa0cd637e', 'India', 'flipkart.com.bolt', 'flipkart.com', 'Bolt Fit', 'boltfit.com'),
('d470d064-1a3b-4715-b54c-76252a59dbb9', 'India', 'croma.in.grand', 'croma.in', 'Grand Mobiles', 'grandmobiles.in'),
('02a2a0a4-5b0a-46fc-879c-3d81f65cd007', 'India', 'croma.in.croma', 'croma.in', 'Croma Electronics', 'croma.electronics.in'),
('9943801f-1cba-40e9-99ca-0a9649e7b2d6', 'India', 'croma.in.gem.electricals', 'croma.in', 'Gem Electricals', 'gem.electricals.com');


insert into sellers(id, producer_id, seller_info_id, state) values
('68d68153-328a-4ee2-a25b-d0d6e47b7f3f', '1bf3cf89-f87c-46fc-8605-028ea638b138', 'c9786789-d94a-4b20-bd3c-85c410cba2e5', 'REGULAR'),
('5076f564-3d64-45b9-afbf-d489b4ae92e3', 'cde77934-d697-4610-a2ee-64387c144f55', 'c9786789-d94a-4b20-bd3c-85c410cba2e5', 'REGULAR'),
('4c38c191-8ea4-499e-ab0f-2596826265a4', '54c7071f-13ed-4ff9-aa2e-2b56f5176e51', 'c9786789-d94a-4b20-bd3c-85c410cba2e5', 'REGULAR'),
('8a5ca387-1dde-4dc0-a1f5-caaedf4ea4bf', '0744cffa-e1b1-4096-b1dc-db7add47a902', '3248e3b3-c002-4bd4-9ae2-29d74d55f447', 'REGULAR'),
('93717558-05a1-426e-b5cf-cb2c8941ddcc', 'b8b71686-2846-4cf0-9008-9d45b136c723', '3248e3b3-c002-4bd4-9ae2-29d74d55f447', 'REGULAR'),
('bf9a733d-f634-4426-9a39-35fbf01c71ef', '1bf3cf89-f87c-46fc-8605-028ea638b138', '239f90d5-e75e-41e8-a3c5-13c06675a694', 'REGULAR'),
('f1fb5a03-1122-4287-ac99-7a5c82f9ec52', 'cde77934-d697-4610-a2ee-64387c144f55', '239f90d5-e75e-41e8-a3c5-13c06675a694', 'REGULAR'),
('d0b19524-2090-4612-a0bc-fecc63bb588a', '54c7071f-13ed-4ff9-aa2e-2b56f5176e51', '239f90d5-e75e-41e8-a3c5-13c06675a694', 'REGULAR'),
('a45e8b2b-0efe-4abb-85e8-849141401bc7', '0744cffa-e1b1-4096-b1dc-db7add47a902', '239f90d5-e75e-41e8-a3c5-13c06675a694', 'REGULAR'),
('0486186c-931a-4878-accf-d09594dc49c2', 'b8b71686-2846-4cf0-9008-9d45b136c723', '239f90d5-e75e-41e8-a3c5-13c06675a694', 'REGULAR'),
('7df73e8c-6cfc-4138-b487-0d841c7b5884', 'cde77934-d697-4610-a2ee-64387c144f55', 'ffffb520-5626-49ec-8d32-297b598cc7e0', 'REGULAR'),
('a7ad46d3-74ff-425e-88ca-fe9cc01b6dd5', '54c7071f-13ed-4ff9-aa2e-2b56f5176e51', 'ffffb520-5626-49ec-8d32-297b598cc7e0', 'REGULAR');


